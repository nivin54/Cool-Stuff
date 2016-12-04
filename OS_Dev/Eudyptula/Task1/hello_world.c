/* lsmod - lists all kernel module.
insmod   - inserts module into kernel module.
modinfo  - it displays information about kernel module.
rmmod - removes the kernel module.
modprobe - adds or removes kernel module.
sudo make -C /lib/modules/$(uname -r)/build M=$PWD modules
*/

#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>

MODULE_LICENSE("Nivark");
MODULE_AUTHOR("NIVIN");
MODULE_DESCRIPTION("A Simple Hello World Module");

static int __init hello_world(void)
{
	printk(KERN_DEBUG "Hello World!");
	return 0;
}

static void __exit un_load(void)
{
	printk(KERN_DEBUG "module unloaded");
}

module_init(hello_world);
module_exit(un_load);





