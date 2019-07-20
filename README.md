# Score Based Inmemory Autocomplete

## Please follow the below steps to build and run:

- Clone this repo
- run - **mvn clean install**
- run - **mvn exec:java -Dexec.mainClass="com.mm.AutoCompleteAPI"**

Before running this, please make sure you have installed maven.

This program use the HashMap for O(1) data retrieval and it uses Java serialization. So when we run the code second time,
we read the object from file instead of calling constructor.

## Input:
    The list of score and key value pair.
## Result:
    This will give a top ten matched keys based on score. If the same score have a more than key 
    then keys are sorted in natural order.

