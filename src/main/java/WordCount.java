/**
 * Illustrates a WordCount in Java
 */

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class WordCount {
    public static void main(String[] args) {

        String inputFile = args[0];
        String outputFile = args[1];

        // Create a Java Spark Context.
        SparkConf conf = new SparkConf().setMaster("local").setAppName("wordCount");

		try(JavaSparkContext sc = new JavaSparkContext(conf)) {
			// Load our input data.
			JavaRDD<String> input = sc.textFile(inputFile);

			// Split up into words.
			JavaRDD<String> words = input.flatMap((x) -> Arrays.asList(x.split(" ")).iterator());

			// Map each word to a (word, 1) pair
			JavaPairRDD<String, Integer> ones = words.mapToPair((x) -> new Tuple2<>(x, 1));

			// ReduceByKey to count the occurrences of each word
			JavaPairRDD<String, Integer> counts = ones.reduceByKey(Integer::sum);

			// Save the word count back out to a text file, causing evaluation.
        	counts.saveAsTextFile(outputFile);
		}
    }
}
