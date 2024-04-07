# Word Count Implementation using Apache Spark

## Requirements:
1. Java 8 or higher
2. Apache Hadoop
3. Apache Spark

## How to run the code:
1. Clone the repository
2. Run hadoop using the following command:
```start-all.sh```
3. Copy the input files directory from local file system to HDFS using the following command:
```hadoop fs -copyFromLocal <local_input_files_path> <hdfs_input_files_path>```
4. Compile the java WordCount code using the following command:
```mvn clean install package```
5. Run the jar file using the following command (add-exports is necessary in case of jdk 17+):
```java --add-exports java.base/sun.nio.ch=ALL-UNNAMED -jar target/wordcount-1.0.jar hdfs://localhost:9000/<path_to_input_files> hdfs://localhost:9000/<path_to_output_files>```
6. Copy the output files from HDFS to local file system using the following command:
```hadoop fs -copyToLocal <hdfs_output_files_path> <local_output_files_path>```
7. Stop hadoop using the following command:
```stop-all.sh```
8. Check the top 10 words using the following command:
      ```sort -n -k2 * | tail -10```

## Results:
The top 10 words in the input files are:
```
[Illustrator:	215
in	265
A	269
[Language:	483
and	555
The	634
[Subtitle:	678
the	834
of	982
by	2018
```
