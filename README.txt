ProducerConsumer: Contains producer, consumer, and main. main starts two threads, producerThread and consumerThread which start the consumer and producer process.

producer: creates random double 'item' and checks if buffer size is equal to 1000, if it is, it waits for the consumer to consume one element. It adds the item to the buffer
icrements the counter of total produced items and adds the value to the sum of the produced items.

consumer: removes the oldest value in the buffer, maintains a value of the sum of the consumed items. 

main: makes two threads and joins them back together

The makefile compiles the files and then runs with 'make run'