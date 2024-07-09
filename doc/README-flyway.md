# version 9.0.0

https://documentation.red-gate.com/fd/release-notes-for-flyway-engine-179732572.html#9.0.1

Breaking changes
- Change default of cleanDisabled to true.

# Tips

### SpringBootでapplication.ymlにConfigurationを設定したのにFlywayがbootRun起動時に実行されない

FlywayのConfigurationが不十分な可能性が高い。DEBUGログを出力することで

```txt
FlywayAutoConfiguration:
    Did not match: XXXXXX
```

のようなログがないか確認して不足しているConditionalを追加対応をする
