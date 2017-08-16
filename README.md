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
  "dataSource": "mysql",
  "mysql": {
    "driver": "com.mysql.jdbc.Driver",
    "databaseURL": "jdbc:mysql://localhost/test",
    "user": "root",
    "password": "zhaoliang123",
    "tables": [
      {
        "tableName": "student",
        "columns": [
          {
            "name": "name",
            "valueType": "string"
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
      },
      {
        "tableName": "teacher",
        "columns": [
          {
            "name": "name",
            "valueType": "string"
          },
          {
            "name": "age",
            "valueType": "string"
          },
          {
            "name": "addr",
            "valueType": "string"
          }
        ]
      }
    ]
  },
  "neo4jGraph": {
    "gremlin.neo4j.directory": "/Users/zhaoliang/test/neo4j",
    "gremlin.graph": "org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph"
  },
  "csv": [
    {
      "path": "/Users/test/file.csv",
      "label": "student",
      "columns": [
        {
          "pos": 0,
          "name": "id",
          "valueType": "string"
        },
        {
          "pos": 1,
          "name": "id",
          "valueType": "string"
        },
        {
          "pos": 2,
          "name": "id",
          "valueType": "string"
        }
      ]
    },
    {
      "path": "/Users/test/file1.csv",
      "label": "teacher",
      "columns": [
        {
          "pos": 0,
          "name": "id",
          "valueType": "string"
        },
        {
          "pos": 1,
          "name": "id",
          "valueType": "string"
        },
        {
          "pos": 2,
          "name": "id",
          "valueType": "string"
        }
      ]
    }
  ]
}

```

## 3 使用

- 解压打包好的文件。
- 配置映射文件`conf/sql2neo4j.json`,注意配置文件中的`dataSource`的值现在支持`csv`和`mysql`两种导入方式。
- 执行：`bin/main.sh`