## maven 使用流程
maven的常见命令
- mvn clean
- mvn compile
- mvn install
- mvn package

组合命令
- mvn clean compile
- mvn clean package
- mvn XX XX -Dmaven.test.skip

排查问题命令：
- mvn dependency:tree
  - 打印依赖树
- mvn dependency:analyze
  - 依赖分析