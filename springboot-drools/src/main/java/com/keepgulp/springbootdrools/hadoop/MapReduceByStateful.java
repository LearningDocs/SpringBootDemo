package com.keepgulp.springbootdrools.hadoop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import com.opencsv.CSVParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.mvel2.optimizers.OptimizerFactory;

/**
 * @description:
 * @author: guodongqing
 * @create: 2018-08-23 20:29
 **/
public class MapReduceByStateful extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        if (strings.length != 2) {
            System.out.printf(
                    "Usage: %s [generic options] <input dir> <output dir>\n",
                    getClass().getSimpleName());
            ToolRunner.printGenericCommandUsage(System.out);
            return -1;
        } else {
            Job job = new Job(this.getConf());
            job.setJarByClass(MapReduceByStateful.class);

            job.setMapperClass(Map.class);
            job.setNumReduceTasks(0);

            job.setInputFormatClass(TextInputFormat.class);
            job.setOutputFormatClass(TextOutputFormat.class);

            FileInputFormat.setInputPaths(job, new Path(strings[0]));
            FileOutputFormat.setOutputPath(job, new Path(strings[1]));

            boolean success = job.waitForCompletion(true);
            return success ? 0 : 1;
        }
    }

    public static class Map extends
            Mapper<LongWritable, Text, Text, Text> {
        private StatefulKnowledgeSession ksession;
        private CSVParser parser;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
            OptimizerFactory.setDefaultOptimizer(OptimizerFactory.SAFE_REFLECTIVE);
            this.parser = new CSVParser();

        }
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            super.cleanup(context);
        }
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            super.map(key, value, context);
        }

    }

}
