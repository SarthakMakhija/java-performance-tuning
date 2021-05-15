# Java Object Size

There are 3 ways in which a java object size can be represented. Shallow, deep and retained object size.

**Shallow Size**

This is the size of an object itself without including the actual size of the other objects that object in question is referring to.

Let's take an example. 

Let's consider a Department class which has a name property of type String. To calculate the shallow size of department, we will include the object header size & the size of the reference to name.

With *UseCompressedOops* enabled, an object header size would be 12 bytes, and a reference would take 4 bytes. This means shallow size of Department object will be 16 bytes.


**Deep Size**

Deep size of an object includes the actual size of other objects that the object in question depends is referring to.

In the previous example, deep size of a department instance will be equal to -
shallow size of department instance 
    + shallow size of String instance 
    + shallow size of byte array (or  char array, depending on the JDK version) that String references

**Retained Size**

Retained size refers to the total amount of memory that would be reclaimed if the object in question is garbage collected.

The tests under ```tuning.performance.size.object``` package assume the following 
- JDK15
- 64 bit JVM
- UseCompressedOops flag is enabled, which means object header is 12bytes in size and an object reference is 4bytes in size
