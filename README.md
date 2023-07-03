Data Engineer Pre-Test
=================================

Howdy,

The goal of this pre-test is for you to take a dataset and process it with Spark 3.x to answer some questions. Also, you are expected to show your skills in writing well-tested maintainable code.

Prerequisites
-------------

You will need to have the following setup in your local environment or wherever you choose to run this test:

* JDK
* sbt
* At least 4GB of RAM

** If you are developing on Windows, then you may need to install [winutils](https://github.com/steveloughran/winutils). See [this](https://mallikarjuna_g.gitbooks.io/spark/content/spark-tips-and-tricks-running-spark-windows.html) for more details.

Setup
-----

* Clone this repo
* Run [this script](data/get-data.sh) to download the dataset (1.3G gziped)
* Run the SparkWordCount app with 
```
$ sbt "runMain com.a8c.pretest.SparkWordCount ../CREED.md 3"
```
If you encounter any issue, feel free to contact us at any point.

Tests
-----

Now that your local environment is all set, add a Scala class `com.a8c.pretest.PostAnalyses` to the project that answers the following questions running in Spark local mode. `SparkWordCount` example is written with RDDs, but you are not limited to it and can use any other approaches for data processing available in Spark 3.x.

### Test 1: Posts Per Blog

Please collect the following summary metrics for the full dataset:

* Minimum number of posts in a blog
* Mean number of posts in a blog
* Maximum number of posts in a blog
* Total number of blogs in the dataset

Bonus: Is there anything else you can tell us about posting activity or any other interesting metric?

### Test 2: Who Likes Posts?

We want to know more about content producers vs consumers in the dataset. What's the percentage of likes coming from people who were NOT authors in this dataset?

### Test 3: Writing a Table

We also want to load this dataset into a Hive table so that we can do ad-hoc queries on it in the future. Please use Spark and write out some files for us to load into HDFS. To keep things simple we're going to keep values in each column as flat scalars: columns like `liker_ids` can be simple delimited strings.

We anticipate that the read load on this table is going to be much more than write load. As such we would like the table to be optimized for querying even at the expense of more data processing during writes. Here are a bunch of example queries that we would like to run on such a table:

```
SELECT count(*) AS posts, lang
  FROM dataset
  WHERE date_gmt BETWEEN '2010-01-01 00:00:00' AND '2010-12-31 23:59:59'
  GROUP BY lang
```

```
SELECT sum(comment_count) AS comments
  FROM dataset
  WHERE
    lang = 'en' AND
    date_gmt BETWEEN '2012-01-01' AND '2012-01-31'
```

```
SELECT sum(like_count) / sum(comment_count) AS ratio
  FROM dataset
```

This is not an exhaustive list. We also want the full dataset loaded in the table: just because a field is not present in one of the above example queries does not mean we don't want it!

Assuming that the dataset in this pretest is the only data that will be written to this table please provide us with:

* A `CREATE TABLE` statement for this table
* The command to run in order to generate the set of files backing this table
* Advice on if we should partition this table and if so how
* Any assumptions that you've made while optimizing this table
