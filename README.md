# DID 응용 개발 프로젝트
hyperledger 기반의 SSI(Self-Sovereign-Identity) holder 만들기 실습

### 준비 사항
- [Android Studio Flamingo](https://developer.android.com/studio): 안드로이드 개발 도구
  - Android 11.0 이상 단말 (virtual 가능)
- [GitHub](https://github.com/): 형상(소스) 관리 도구
  - [Git 이란?](https://git-scm.com/)
    소스 코드의 버전을 분산 형식으로 저장, 관리하는 도구
  - repository: 소스 저장소. 원격(remote), 로컬(local) 존재
    - [indy-holder](https://github.com/bdgen/indy-holder)
- 공유 가능한 slide, spread sheet, word 툴
  - 각종 명세 문서를 작성 하기 위한 문서 도구
  ex) MS-office, Google Docs, [PlantUML](https://plantuml.com/ko/) 등

### repository 생성
초기 repository인 [indy-holder](https://github.com/bdgen/indy-holder)를 [fork](https://docs.github.com/ko/get-started/quickstart/fork-a-repo#forking-a-repository)하여 팀 별 신규 repository를 생성한 후 작업합니다.

### 학습 목표
- DID 개념을 이해하고 DID를 이용한 holder 앱을 설계, 구현할 수 있다.
- kotlin 언어를 사용하여 안드로이드 앱을 개발할 수 있다.
- git의 개념을 이해하고 협업하여 소스를 관리할 수 있다.
- 기획, 셜계 및 구현 사항에 대한 명세 문서를 작성할 수 있다.

### project, workspace setting
clone한 repository에 아래 세팅이 되어 있는지 확인합니다.
- build.gradle
  - 언어: Kotlin 1.8.0 또는 java 8
  - 빌드 툴: AGP(Android Gradle Plugin) 8.0.2
- build.gradle(:app)
  - minSdk: 30 (android 11) 이상
  - targetSdk: 33 (최신 버전)
  - compile 언어: java 8
- [hyperledger indy-sdk](https://github.com/hyperledger/indy-sdk) 내 indy library import
  - libindy.so
  - libindy_shared.so
  - libc++_shared.so
  - libjnidispatch.so
  
### project structure
- /app/libs: 프로젝트에서 사용하는 library 파일을 import. [build.gradle](app/build.gradle) 파일에 dependency 추가
- /app/src/main/java: 앱을 구동하기 위한 소스를 작업하는 directory.
- /app/src/main/jniLibs: indy library를 import. 각 CPU architecture 별 libindy 라이브러리(~.so)를 import한다.
- /app/src/main/res: UI 구성을 위한 각종 정적 resource 파일 directory

### sdk API
##### issue
- credential offer: 발급을 위한 제안 (entry point)
- credReq 생성: credential 발급을 위한 metaData 등의 요청 정보를 생성
    Anoncreds.proverCreateCredentialReq(wallet, did, credOffer, credDefJson, masterSecret)

##### credential
- credential 저장
    Anoncreds.proverStoreCredential(wallet, @Nullable credId, credReqMetadataJson, credJson, credDefJson, @Nullable revRegDefJson)
- 저장한 credential 조회
    Anoncreds.proverGetCredentials(wallet, filterString)

### issuer API
- issuer domain
    baseUrl = "http://211.37.24.246:8080/"
- [swaggerUI(API 명세)](http://211.37.24.246:8080/webjars/swagger-ui/index.html)