# avro2json
This project shows how to create classes from Avro schema with the Avro Maven plugin
and how to serialize and deserialize these classes into/from JSON.
This can be useful, when you are using Avro internally, but you have to interoperate
with systems which can only handle JSON. It is also useful, if you want to manage
the creation of JSON via a schema.

Thanks to Jeff Li for his very good blog posts about Avro:

- [http://blog.jeffli.me/blog/2014/02/06/avro-cookbook-part-i](http://blog.jeffli.me/blog/2014/02/06/avro-cookbook-part-i)
- [http://blog.jeffli.me/blog/2014/04/05/avro-cookbook-part-ii](http://blog.jeffli.me/blog/2014/04/05/avro-cookbook-part-ii)
- [http://blog.jeffli.me/blog/2014/04/08/avro-cookbook-part-iii](http://blog.jeffli.me/blog/2014/04/08/avro-cookbook-part-iii)

## Usage

    mvn clean install
    java -jar target/avro2json-1.0-SNAPSHOT.jar


