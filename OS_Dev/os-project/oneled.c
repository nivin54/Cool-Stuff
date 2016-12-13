#include <linux/init.h>
#include <linux/module.h>
#include <asm/io.h>
#include <mach/platform.h>
#include <linux/timer.h>
#include <linux/device.h>
#include <linux/err.h>
#include <linux/gpio.h>
#include <linux/interrupt.h>

MODULE_LICENSE("GPL");


//Instead of using native gpio library, we are writing from scratch.
struct GpioRegisters
{
    uint32_t GPFSEL[6]; // defines the function of the port.
    uint32_t Reserved1;
    uint32_t GPSET[2]; // sets the value for corresponding pin.
    uint32_t Reserved2;
    uint32_t GPCLR[2]; // clears the value 
};

struct GpioRegisters *s_pGpioRegisters;
static const int LedGpioPin1 = 21; // GPIO Output pin
static const int LedGpioPin2 = 20;
static const int gpioButton = 23; // Button that is used for toggling.
static bool on = false;
static unsigned int irqNumber;  // Irq Number to listens to. 

static irq_handler_t  ebbgpio_irq_handler(unsigned int irq, void *dev_id, struct pt_regs *regs); // defines the interrupt handler function

static void SetGPIOFunction(int GPIO, int functionCode)
{
    int registerIndex = GPIO / 10;
    int bit = (GPIO % 10) * 3;
    unsigned oldValue = s_pGpioRegisters-> GPFSEL[registerIndex];
    unsigned mask = 0b111 << bit;
    printk("Changing function of GPIO%d from %x to %x\n",
           GPIO,
           (oldValue >> bit) & 0b111,
           functionCode);
    s_pGpioRegisters-> GPFSEL[registerIndex] =
        (oldValue & ~mask) | ((functionCode << bit) & mask);
}

static void SetGPIOOutputValue(int GPIO, bool outputValue)
{
    if (outputValue)
        s_pGpioRegisters-> GPSET[GPIO / 32] = (1 << (GPIO % 32));
    else
        s_pGpioRegisters-> GPCLR[GPIO / 32] = (1 << (GPIO % 32));
}

static int __init LedBlinkModule_init(void)
{
   
    printk(KERN_INFO "GPIO_TEST Welcome");
    s_pGpioRegisters =
        (struct GpioRegisters *)__io_address(GPIO_BASE);
    SetGPIOFunction( LedGpioPin1, 0b001); //Output
    SetGPIOFunction( LedGpioPin2, 0b001); //Output
    SetGPIOFunction( gpioButton, 0); //Input
    SetGPIOOutputValue( LedGpioPin1, !on);
    SetGPIOOutputValue( LedGpioPin2, on);
    irqNumber = gpio_to_irq(gpioButton);
    printk(KERN_INFO "GPIO_TEST: The interrupt number  is: %d\n", irqNumber);
    //IRQ_HANDLED, request_irq, is a predefined function, that helps to register interupt lines. 

    int result = request_irq(irqNumber,
                        (irq_handler_t) ebbgpio_irq_handler, 
                        IRQF_TRIGGER_RISING,
                        "ebb_gpio_handler",
                        NULL);
 
   printk(KERN_INFO "GPIO_TEST: The interrupt request result is: %d\n", result);
   return result;	
}

static void __exit LedBlinkModule_exit(void)
{
    SetGPIOFunction( LedGpioPin1, 0); //Configure the pin as input
    SetGPIOFunction( LedGpioPin2, 0); //Output
    free_irq(irqNumber, NULL);
    gpio_free(gpioButton);
	
}
static irq_handler_t ebbgpio_irq_handler(unsigned int irq, void *dev_id, struct pt_regs *regs){
   on = !on;                          
   SetGPIOOutputValue( LedGpioPin1, !on);
   SetGPIOOutputValue( LedGpioPin2, on);         
   printk(KERN_INFO "GPIO_TEST: Interrupt! (button state is)");		
   return (irq_handler_t) IRQ_HANDLED;   
}



module_init(LedBlinkModule_init);
module_exit(LedBlinkModule_exit);

