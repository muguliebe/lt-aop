# lt-aop
lecture for aspect oriented programming

## before start
- 환경
  - 코틀린(JDK 11+)
  - gradle 7.6+
  - Database
    - H2
      - ID: sa
      - PW: 1234
    - fileDB 로 사용하며, 프로젝트 내 ./db 에 위치
    - 스프링 부트 구동 시, flyway 로 스키마 생성
      - src/main/resources/db.migration 에 위치

### JDK 설치 ( by SDKMan)
1. SDKMan 설치 파일 다운로드
```bash
  curl -s "https://get.sdkman.io" | bash
```

2. 설치
```bash
  source "$HOME/.sdkman/bin/sdkman-init.sh"
  sdk version
```

### JDK 11 설치
```bash
  sdk install java 11.0.17-zulu
```
