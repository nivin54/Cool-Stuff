obj-m += oneled.o
CCPREFIX=/home/os/tools/arm-bcm2708/arm-bcm2708-linux-gnueabi/bin/arm-bcm2708-linux-gnueabi-
all:
	make ARCH=arm CROSS_COMPILE=$(CCPREFIX) -C /home/os/linux M=$(PWD) modules
clean:
	make -C /home/os/linux M=$(PWD) clean
