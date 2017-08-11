# sql2graph

## 1. 项目打包

- `git clone git@github.com:openmg/sql2graph.git`
- `mvn clean package`
- `tar -xvf ${BASEDIR}/target/sql2graph-1.0-SNAPSHOT-mysql2neo4j.tar.gz`

## 2. 文件配置
> 配置sql数据库表的映射关系。

example:
```json
{
  "mysql": {
    "driver": "com.mysql.jdbc.Driver",
    "databaseURL": "jdbc:mysql://localhost/test",
    "user": "root", 
    "password": "zhaoliang123",
    "table": {
      "tableName": "student", // 表名
      "columns": [ //表名所包含的字段
        {
          "name": "name", // column 名
          "valueType": "string" // 字段类型
        },
        {
          "name": "age",
          "valueType": "string"
        },
        {
          "name": "teacher",
          "valueType": "string"
        },
        {
          "name": "addr",
          "valueType": "string"
        }
      ]
    }
  },
  "neo4jGraph": { // 导入到图的配置。
    "gremlin.neo4j.directory": "/Users/zhaoliang/test/neo4j",
    "gremlin.graph": "org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph"
  }
}

```

## 3 使用

- 解压打包好的文件。
- 配置映射文件`conf/sql2neo4j.json`
- 执行：`bin/main.sh`