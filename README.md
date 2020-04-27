# Image-Processor
An image processor in JAVA that uses Bit Manipulation to create 3 separate instances of an image, Black and White, Sepia and Negative 
 
## Description for Nerds 

A pixel is made up fo 3 components 

**Alpha - A**
**Red - R**
**Green - G**
**Blue - B**

Each of these components have a value between 0 and 255
Where 0 means the component is missing and 255 meaning the component is fully present.

Therefore we need 32 bits to represent a pixel  

/argb.png

And for a 2D image we can arrange those pixels in rows and columns 

And manipulating those rows and columns of pixels we can change how an image looks 