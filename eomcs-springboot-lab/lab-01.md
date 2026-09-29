# 1장. 실습 준비

실습에 필요한 개발 도구를 준비한다. Spring Initializr를 통해 스프링 부트 프로젝트를 생성한다.

## 개발 도구 설치

### VS Code 설치

실습 코드를 작성하고 실행하기 위해 편집기로 VS Code(Visual Studio Code)를 설치한다.
VS Code에 Java와 Spring Boot 확장 프로그램을 설치하면 코드 자동 완성, 디버깅, 스프링 부트 프로젝트 생성·실행 기능을 사용할 수 있다.

#### macOS

macOS는 다음 두 가지 방법 중 하나로 설치한다.

**방법 1: 설치 파일로 설치**

1. [https://code.visualstudio.com/download](https://code.visualstudio.com/download) 에서 `macOS` 설치 파일을 내려받는다.
2. 내려받은 파일을 열고 `Visual Studio Code.app`을 `응용 프로그램(Applications)` 폴더로 끌어다 놓는다.
3. `응용 프로그램` 폴더에서 `Visual Studio Code`를 실행한다.

**방법 2: Homebrew로 설치**

터미널에서 다음 명령을 실행한다.

```bash
brew install --cask visual-studio-code
```

**`code` 명령 등록**

터미널에서 `code` 명령으로 VS Code를 실행할 수 있도록 등록한다.

1. VS Code를 실행한다.
2. `Cmd + Shift + P`를 눌러 명령 팔레트(Command Palette)를 연다.
3. `shell command`를 입력하고 **Shell Command: Install 'code' command in PATH**를 선택한다.
4. 터미널을 새로 연다.

> Homebrew로 설치한 경우에는 `code` 명령이 자동으로 등록되므로 이 과정을 건너뛰어도 된다.

#### Windows 11

Windows 11은 다음 두 가지 방법 중 하나로 설치한다.

**방법 1: winget으로 설치**

PowerShell 또는 명령 프롬프트에서 다음 명령을 실행한다.

```powershell
winget install --id Microsoft.VisualStudioCode -e --source winget
```

**방법 2: 설치 파일로 설치**

1. [https://code.visualstudio.com/download](https://code.visualstudio.com/download) 에서 `Windows`의 **User Installer**(x64)를 내려받는다.
2. 설치 파일을 실행하고 대부분의 옵션은 기본값으로 진행한다. **추가 작업 선택** 화면에서는 다음 항목을 확인한다.
   - **"Code(으)로 열기" 작업을 Windows 탐색기 파일의 상황에 맞는 메뉴에 추가**: 선택한다.
   - **"Code(으)로 열기" 작업을 Windows 탐색기 디렉터리의 상황에 맞는 메뉴에 추가**: 선택한다.
   - **PATH에 추가(다시 시작한 후 사용 가능)**: 선택한다. (기본값)

`PATH`에 추가하면 PowerShell이나 명령 프롬프트에서 `code` 명령으로 VS Code를 실행할 수 있다.
설치 후에는 PowerShell 또는 명령 프롬프트를 새로 열어야 `code` 명령이 인식된다.

> 탐색기 메뉴에 "Code(으)로 열기"를 추가하면 폴더를 마우스 오른쪽 버튼으로 클릭하여 바로 VS Code로 열 수 있다.
> Windows 11에서는 마우스 오른쪽 버튼 메뉴의 `추가 옵션 표시`를 눌러야 보일 수 있다.

#### 설치 확인

터미널(macOS) 또는 PowerShell(Windows)에서 버전을 출력하여 설치를 확인한다.

```bash
code --version
```

다음과 같이 버전 정보가 출력되면 정상적으로 설치된 것이다.

```
1.xxx.x
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
arm64
```

#### 확장 프로그램 설치

Java와 Spring Boot 개발에 필요한 확장 프로그램을 설치한다.

| 확장 프로그램 | 식별자(ID) | 설명 |
| --- | --- | --- |
| Extension Pack for Java | `vscjava.vscode-java-pack` | Java 언어 지원, 디버거, 테스트 실행, Maven/Gradle 지원 등을 묶은 확장 팩 |
| Spring Boot Extension Pack | `vmware.vscode-boot-dev-pack` | Spring Boot Tools, Spring Initializr, Spring Boot Dashboard를 묶은 확장 팩 |

다음 두 가지 방법 중 하나로 설치한다.

**방법 1: VS Code 화면에서 설치**

1. 왼쪽 활동 막대에서 **확장(Extensions)** 아이콘을 클릭한다. (단축키: macOS `Cmd + Shift + X`, Windows `Ctrl + Shift + X`)
2. 검색 창에 `Extension Pack for Java`를 입력하고, 게시자가 **Microsoft**인 항목의 `설치` 버튼을 누른다.
3. 검색 창에 `Spring Boot Extension Pack`을 입력하고, 게시자가 **VMware**인 항목의 `설치` 버튼을 누른다.

**방법 2: `code` 명령으로 설치**

터미널(macOS) 또는 PowerShell(Windows)에서 다음 명령을 실행한다.

```bash
code --install-extension vscjava.vscode-java-pack
code --install-extension vmware.vscode-boot-dev-pack
```

설치된 확장 프로그램 목록은 다음 명령으로 확인한다.

```bash
code --list-extensions
```

목록에 `vscjava.vscode-java-pack`과 `vmware.vscode-boot-dev-pack`이 포함되어 있으면 정상적으로 설치된 것이다.

> Java 확장 프로그램은 설치된 JDK를 사용하여 코드를 컴파일하고 실행한다.
> 뒤에서 JDK를 설치한 후에는 VS Code를 다시 시작해야 JDK가 인식된다.

#### 한국어 언어 팩 설치 (선택)

VS Code 메뉴를 한국어로 사용하려면 **Korean Language Pack for Visual Studio Code**(`MS-CEINTL.vscode-language-pack-ko`)를 설치한다.
설치 후 오른쪽 아래에 표시되는 `Change Language and Restart` 버튼을 누르면 VS Code가 한국어로 다시 시작된다.

### Git CLI 클라이언트 설치

실습 소스를 내려받고 버전을 관리하기 위해 Git CLI 클라이언트를 설치한다.

#### macOS

macOS는 다음 두 가지 방법 중 하나로 설치한다.

**방법 1: Xcode Command Line Tools 설치**

터미널에서 다음 명령을 실행한다. 설치 창이 뜨면 `설치` 버튼을 눌러 진행한다.

```bash
xcode-select --install
```

Git 외에 컴파일러 등 개발에 필요한 기본 도구가 함께 설치된다.

**방법 2: Homebrew로 설치**

Homebrew를 사용하면 Apple이 제공하는 버전보다 최신 버전의 Git을 설치할 수 있다.
Homebrew가 없다면 [https://brew.sh](https://brew.sh) 에 안내된 명령으로 먼저 설치한다.

```bash
brew install git
```

#### Windows 11

Windows 11은 다음 두 가지 방법 중 하나로 설치한다.

**방법 1: winget으로 설치**

PowerShell 또는 명령 프롬프트에서 다음 명령을 실행한다.

```powershell
winget install --id Git.Git -e --source winget
```

**방법 2: 설치 파일로 설치**

1. [https://git-scm.com/downloads/win](https://git-scm.com/downloads/win) 에서 설치 파일을 내려받는다.
2. 설치 파일을 실행하고 대부분의 옵션은 기본값으로 진행한다. 다음 항목은 확인한다.
   - **Choosing the default editor used by Git**: 익숙한 편집기를 선택한다. (예: `Use Visual Studio Code as Git's default editor`)
   - **Adjusting the name of the initial branch in new repositories**: `Override the default branch name for new repositories`를 선택하고 `main`을 입력한다.
   - **Adjusting your PATH environment**: `Git from the command line and also from 3rd-party software`(권장)를 선택한다.
   - **Configuring the line ending conversions**: `Checkout Windows-style, commit Unix-style line endings`를 선택한다.

설치 후에는 PowerShell 또는 명령 프롬프트를 새로 열어야 `git` 명령이 인식된다.
설치 파일에 포함된 **Git Bash**를 사용하면 macOS/Linux와 같은 셸 명령을 사용할 수 있다.

#### 설치 확인

터미널(macOS) 또는 PowerShell(Windows)에서 버전을 출력하여 설치를 확인한다.

```bash
git --version
```

다음과 같이 버전 정보가 출력되면 정상적으로 설치된 것이다.

```
git version 2.xx.x
```

#### 사용자 정보 설정

커밋에 기록될 사용자 이름과 이메일을 설정한다. 최초 한 번만 설정하면 된다.

```bash
git config --global user.name "홍길동"
git config --global user.email "hong@example.com"
git config --global init.defaultBranch main
```

설정한 내용은 다음 명령으로 확인한다.

```bash
git config --global --list
```

### JDK 설치

스프링 부트 애플리케이션을 컴파일하고 실행하기 위해 JDK(Java Development Kit)를 설치한다.
Spring Boot 4.x는 **Java 17 이상**이 필요하다. 이 과정에서는 현재 LTS(장기 지원) 버전인 **Java 25**를 사용한다.
JDK 배포판은 무료로 사용할 수 있는 **Eclipse Temurin**(Adoptium)을 설치한다.

> 이미 다른 버전의 JDK가 설치되어 있어도 함께 설치할 수 있다. 설치 후 `JAVA_HOME`이 Java 25를 가리키도록 설정한다.

#### macOS

macOS는 다음 세 가지 방법 중 하나로 설치한다.

**방법 1: SDKMAN!으로 설치**

SDKMAN!(`sdk` 명령)은 JDK, Gradle, Maven 같은 개발 도구의 여러 버전을 설치하고 전환해 주는 도구이다.
프로젝트마다 다른 JDK 버전을 사용해야 할 때 편리하다.

1. 터미널에서 다음 명령을 실행하여 SDKMAN!을 설치한다.

   ```bash
   curl -s "https://get.sdkman.io" | bash
   ```

2. 설치가 끝나면 터미널을 새로 열거나, 현재 터미널에서 다음 명령을 실행하여 SDKMAN!을 활성화한다.

   ```bash
   source "$HOME/.sdkman/bin/sdkman-init.sh"
   ```

3. `sdk` 명령이 동작하는지 확인한다.

   ```bash
   sdk version
   ```

4. 설치할 수 있는 JDK 목록에서 Temurin 25 버전을 찾는다. `Identifier` 열의 값이 설치할 때 사용할 이름이다.

   ```bash
   sdk list java | grep "25.*tem"
   ```

   ```
   Temurin  |     | 25.0.x    | tem     |            | 25.0.x-tem
   ```

5. 앞에서 찾은 Identifier로 JDK를 설치한다. 기본 버전으로 사용할지 묻는 질문에는 `Y`를 입력한다.

   ```bash
   sdk install java 25.0.x-tem
   ```

   `25.0.x` 부분은 목록에서 확인한 실제 버전으로 바꿔 입력한다.

JDK는 `~/.sdkman/candidates/java` 폴더에 설치되며, SDKMAN!이 `JAVA_HOME`과 `PATH`를 자동으로 설정한다.
따라서 방법 1로 설치한 경우에는 아래 **JAVA_HOME 설정**을 건너뛴다.

여러 버전의 JDK를 설치한 경우 다음 명령으로 사용할 버전을 확인하고 전환한다.

```bash
sdk current java                   # 현재 사용 중인 버전 확인
sdk use java 25.0.x-tem            # 현재 터미널에서만 전환
sdk default java 25.0.x-tem        # 기본 버전으로 지정
```

> SDKMAN!으로 설치한 JDK는 `/usr/libexec/java_home` 명령에 나타나지 않는다. Homebrew나 설치 파일로 설치한 JDK와 섞어 쓰면 어떤 JDK가 사용되는지 헷갈릴 수 있으므로 한 가지 방법으로 통일하는 것이 좋다.

**방법 2: 설치 파일로 설치**

1. [https://adoptium.net/temurin/releases](https://adoptium.net/temurin/releases) 에서 다음 조건으로 설치 파일을 내려받는다.
   - **Operating System**: `macOS`
   - **Architecture**: Apple Silicon(M1 이후) 맥은 `aarch64`, Intel 맥은 `x64`
   - **Package Type**: `JDK`
   - **Version**: `25 - LTS`
2. 내려받은 `.pkg` 파일을 실행하고 기본값으로 설치를 진행한다.

> 내 맥의 칩 종류는 화면 왼쪽 위의 `Apple 메뉴 > 이 Mac에 관하여`에서 확인한다.

**방법 3: Homebrew로 설치**

터미널에서 다음 명령을 실행한다.

```bash
brew install --cask temurin@25
```

JDK는 `/Library/Java/JavaVirtualMachines/temurin-25.jdk` 폴더에 설치된다.

**JAVA_HOME 설정** (방법 2, 3으로 설치한 경우)

macOS가 제공하는 `java_home` 명령으로 Java 25의 설치 경로를 찾아 `JAVA_HOME`에 설정한다.
다음 명령을 실행하여 셸 설정 파일(`~/.zshrc`)에 등록한다.

```bash
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 25)' >> ~/.zshrc
echo 'export PATH="$JAVA_HOME/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

설치된 JDK 목록은 다음 명령으로 확인할 수 있다.

```bash
/usr/libexec/java_home -V
```

#### Windows 11

Windows 11은 다음 두 가지 방법 중 하나로 설치한다.

**방법 1: winget으로 설치**

PowerShell 또는 명령 프롬프트에서 다음 명령을 실행한다.

```powershell
winget install --id EclipseAdoptium.Temurin.25.JDK -e --source winget
```

JDK는 `C:\Program Files\Eclipse Adoptium\jdk-25.x.x.x-hotspot` 폴더에 설치되고, `bin` 폴더는 `PATH`에 자동으로 추가된다.
`JAVA_HOME`은 자동으로 설정되지 않으므로 아래 **JAVA_HOME 설정**을 진행한다.

**방법 2: 설치 파일로 설치**

1. [https://adoptium.net/temurin/releases](https://adoptium.net/temurin/releases) 에서 다음 조건으로 설치 파일을 내려받는다.
   - **Operating System**: `Windows`
   - **Architecture**: `x64`
   - **Package Type**: `JDK`
   - **Version**: `25 - LTS`
2. 내려받은 `.msi` 파일을 실행한다.
3. **Custom Setup** 화면에서 다음 항목을 확인한다.
   - **Add to PATH**: 기본값(설치됨)을 유지한다.
   - **Set JAVA_HOME variable**: 항목을 클릭하고 `Will be installed on local hard drive`를 선택한다.
4. 나머지는 기본값으로 설치를 진행한다.

설치 파일에서 `Set JAVA_HOME variable`을 선택했다면 아래 **JAVA_HOME 설정**은 건너뛴다.

**JAVA_HOME 설정**

PowerShell에서 다음 명령을 실행하여 설치된 JDK 폴더를 `JAVA_HOME` 사용자 환경 변수로 등록한다.

```powershell
$jdk = (Get-ChildItem "C:\Program Files\Eclipse Adoptium" -Directory -Filter "jdk-25*" | Select-Object -First 1).FullName
[Environment]::SetEnvironmentVariable("JAVA_HOME", $jdk, "User")
```

또는 `시작 > "시스템 환경 변수 편집" 검색 > 환경 변수` 화면에서 다음과 같이 직접 등록해도 된다.

- **변수 이름**: `JAVA_HOME`
- **변수 값**: `C:\Program Files\Eclipse Adoptium\jdk-25.x.x.x-hotspot` (실제 설치된 폴더 이름)

환경 변수를 변경한 후에는 PowerShell 또는 명령 프롬프트를 새로 열어야 적용된다.

#### 설치 확인

터미널(macOS) 또는 PowerShell(Windows)에서 버전을 출력하여 설치를 확인한다.

```bash
java -version
javac -version
```

다음과 같이 `25`로 시작하는 버전 정보가 출력되면 정상적으로 설치된 것이다.

```
openjdk version "25.0.x" 2025-xx-xx LTS
OpenJDK Runtime Environment Temurin-25.0.x+x (build 25.0.x+x-LTS)
OpenJDK 64-Bit Server VM Temurin-25.0.x+x (build 25.0.x+x-LTS, mixed mode, sharing)
javac 25.0.x
```

`JAVA_HOME` 설정도 확인한다.

```bash
# macOS
echo $JAVA_HOME
```

```powershell
# Windows (PowerShell)
echo $env:JAVA_HOME
```

JDK 설치 폴더 경로가 출력되면 정상적으로 설정된 것이다.
다른 버전이 출력되면 이전에 설치한 JDK가 `PATH`에서 먼저 검색되는 것이므로 `PATH`의 순서를 확인한다.

### Gradle 설치

Gradle은 소스 코드 컴파일, 라이브러리(의존성) 다운로드, 테스트, 패키징 같은 빌드 작업을 자동화하는 빌드 도구이다.
Gradle은 JVM 위에서 실행되므로 **앞에서 JDK를 먼저 설치해야 한다.** Java 25로 Gradle을 실행하려면 Gradle **9.1 이상**이 필요하다.

> Spring Initializr로 생성한 프로젝트에는 Gradle Wrapper(`gradlew`)가 포함되어 있어서 Gradle을 따로 설치하지 않아도 빌드할 수 있다.
> 여기서는 `gradle` 명령을 직접 사용해 보기 위해 설치한다.

#### macOS

macOS는 다음 두 가지 방법 중 하나로 설치한다.

**방법 1: SDKMAN!으로 설치**

JDK 설치에서 SDKMAN!을 설치했다면 다음 명령 한 줄로 최신 버전의 Gradle을 설치할 수 있다.
기본 버전으로 사용할지 묻는 질문에는 `Y`를 입력한다.

```bash
sdk install gradle
```

SDKMAN!을 아직 설치하지 않았다면 [JDK 설치](#jdk-설치)의 **방법 1: SDKMAN!으로 설치**에서 1~3단계를 먼저 진행한다.

설치할 수 있는 Gradle 버전 목록을 확인하거나 특정 버전을 설치하려면 다음 명령을 사용한다.

```bash
sdk list gradle                    # 설치 가능한 버전 목록 확인
sdk install gradle 9.x.x           # 특정 버전 설치
sdk current gradle                 # 현재 사용 중인 버전 확인
```

Gradle은 `~/.sdkman/candidates/gradle` 폴더에 설치되며, SDKMAN!이 `PATH`를 자동으로 설정한다.

**방법 2: Homebrew로 설치**

터미널에서 다음 명령을 실행한다.

```bash
brew install gradle
```

> Homebrew로 Gradle을 설치하면 Gradle이 사용할 OpenJDK가 함께 설치될 수 있다.
> 이 경우에도 `JAVA_HOME`이 설정되어 있으면 Gradle은 `JAVA_HOME`의 JDK를 사용한다.
> JDK를 SDKMAN!으로 설치했다면 Gradle도 SDKMAN!으로 설치하는 것이 관리하기 편하다.

#### Windows 11

Windows 11은 다음 두 가지 방법 중 하나로 설치한다.

> SDKMAN!은 Windows에서 직접 실행되지 않는다. (WSL 환경에서만 사용 가능)

**방법 1: winget으로 설치**

PowerShell 또는 명령 프롬프트에서 다음 명령을 실행한다.

```powershell
winget install --id Gradle.Gradle -e --source winget
```

설치 후에는 PowerShell 또는 명령 프롬프트를 새로 열어야 `gradle` 명령이 인식된다.

**방법 2: 압축 파일로 설치**

1. [https://gradle.org/releases](https://gradle.org/releases) 에서 최신 버전의 **binary-only** 압축 파일(`gradle-9.x.x-bin.zip`)을 내려받는다.
2. `C:\Gradle` 폴더를 만들고, 내려받은 파일의 압축을 이 폴더에 푼다.
   압축을 풀면 `C:\Gradle\gradle-9.x.x` 폴더가 생긴다.
3. `시작 > "시스템 환경 변수 편집" 검색 > 환경 변수` 화면에서 다음과 같이 설정한다.
   - 사용자 변수에서 `새로 만들기`를 눌러 다음 변수를 추가한다.
     - **변수 이름**: `GRADLE_HOME`
     - **변수 값**: `C:\Gradle\gradle-9.x.x` (실제 압축을 푼 폴더 이름)
   - 사용자 변수의 `Path`를 선택하고 `편집 > 새로 만들기`를 눌러 `%GRADLE_HOME%\bin`을 추가한다.
4. PowerShell 또는 명령 프롬프트를 새로 연다.

> `GRADLE_HOME` 변수를 사용하면 나중에 Gradle 버전을 올릴 때 `GRADLE_HOME`의 값만 바꾸면 된다.

PowerShell에서 다음 명령으로 환경 변수를 설정할 수도 있다.

```powershell
$gradle = (Get-ChildItem "C:\Gradle" -Directory -Filter "gradle-*" | Select-Object -First 1).FullName
[Environment]::SetEnvironmentVariable("GRADLE_HOME", $gradle, "User")
$path = [Environment]::GetEnvironmentVariable("Path", "User")
[Environment]::SetEnvironmentVariable("Path", "$path;$gradle\bin", "User")
```

#### 설치 확인

터미널(macOS) 또는 PowerShell(Windows)에서 버전을 출력하여 설치를 확인한다.

```bash
gradle -v
```

다음과 같이 Gradle 버전과 함께 **Launcher JVM**에 앞에서 설치한 JDK 버전(`25.x.x`)이 출력되면 정상적으로 설치된 것이다.

```
------------------------------------------------------------
Gradle 9.x.x
------------------------------------------------------------

Build time:    20xx-xx-xx xx:xx:xx UTC
Revision:      xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

Kotlin:        2.x.x
Groovy:        4.x.x
Ant:           Apache Ant(TM) version 1.10.x
Launcher JVM:  25.0.x (Eclipse Adoptium 25.0.x+x-LTS)
Daemon JVM:    /.../jdk-25... (no JDK specified, using current Java home)
OS:            Mac OS X 26.x aarch64
```

`Launcher JVM`에 다른 버전의 JDK가 출력되면 `JAVA_HOME` 설정을 다시 확인한다.

### Maven 설치

Maven은 Gradle과 함께 Java 프로젝트에서 가장 많이 사용하는 빌드 도구이다.
`pom.xml` 파일에 프로젝트 정보와 의존 라이브러리를 선언하면 Maven이 라이브러리 다운로드, 컴파일, 테스트, 패키징을 처리한다.
Maven도 JVM 위에서 실행되므로 **앞에서 JDK를 먼저 설치해야 한다.**
이 과정에서는 현재 안정 버전인 **Maven 3.9.x**를 사용한다.

> Spring Initializr로 생성한 Maven 프로젝트에는 Maven Wrapper(`mvnw`)가 포함되어 있어서 Maven을 따로 설치하지 않아도 빌드할 수 있다.
> 여기서는 `mvn` 명령을 직접 사용해 보기 위해 설치한다.

#### macOS

macOS는 다음 두 가지 방법 중 하나로 설치한다.

**방법 1: SDKMAN!으로 설치**

JDK 설치에서 SDKMAN!을 설치했다면 다음 명령 한 줄로 Maven을 설치할 수 있다.
기본 버전으로 사용할지 묻는 질문에는 `Y`를 입력한다.

```bash
sdk install maven
```

SDKMAN!을 아직 설치하지 않았다면 [JDK 설치](#jdk-설치)의 **방법 1: SDKMAN!으로 설치**에서 1~3단계를 먼저 진행한다.

설치할 수 있는 Maven 버전 목록을 확인하거나 특정 버전을 설치하려면 다음 명령을 사용한다.

```bash
sdk list maven                     # 설치 가능한 버전 목록 확인
sdk install maven 3.9.x            # 특정 버전 설치
sdk current maven                  # 현재 사용 중인 버전 확인
```

Maven은 `~/.sdkman/candidates/maven` 폴더에 설치되며, SDKMAN!이 `PATH`를 자동으로 설정한다.

> 목록에 `4.0.0-rc-x`처럼 `rc`가 붙은 버전은 아직 정식 출시 전의 시험 버전이므로 설치하지 않는다.

**방법 2: Homebrew로 설치**

터미널에서 다음 명령을 실행한다.

```bash
brew install maven
```

> Homebrew로 Maven을 설치하면 Maven이 사용할 OpenJDK가 함께 설치될 수 있다.
> 이 경우에도 `JAVA_HOME`이 설정되어 있으면 Maven은 `JAVA_HOME`의 JDK를 사용한다.
> JDK를 SDKMAN!으로 설치했다면 Maven도 SDKMAN!으로 설치하는 것이 관리하기 편하다.

#### Windows 11

Windows 11은 압축 파일을 내려받아 설치한다.

> SDKMAN!은 Windows에서 직접 실행되지 않는다. (WSL 환경에서만 사용 가능)
> 또한 winget에는 Apache Maven 공식 패키지가 없다.

**압축 파일로 설치**

1. [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi) 에서 **Binary zip archive**(`apache-maven-3.9.x-bin.zip`)를 내려받는다.
2. `C:\Maven` 폴더를 만들고, 내려받은 파일의 압축을 이 폴더에 푼다.
   압축을 풀면 `C:\Maven\apache-maven-3.9.x` 폴더가 생긴다.
3. `시작 > "시스템 환경 변수 편집" 검색 > 환경 변수` 화면에서 다음과 같이 설정한다.
   - 사용자 변수에서 `새로 만들기`를 눌러 다음 변수를 추가한다.
     - **변수 이름**: `MAVEN_HOME`
     - **변수 값**: `C:\Maven\apache-maven-3.9.x` (실제 압축을 푼 폴더 이름)
   - 사용자 변수의 `Path`를 선택하고 `편집 > 새로 만들기`를 눌러 `%MAVEN_HOME%\bin`을 추가한다.
4. PowerShell 또는 명령 프롬프트를 새로 연다.

> `MAVEN_HOME` 변수를 사용하면 나중에 Maven 버전을 올릴 때 `MAVEN_HOME`의 값만 바꾸면 된다.

PowerShell에서 다음 명령으로 환경 변수를 설정할 수도 있다.

```powershell
$maven = (Get-ChildItem "C:\Maven" -Directory -Filter "apache-maven-*" | Select-Object -First 1).FullName
[Environment]::SetEnvironmentVariable("MAVEN_HOME", $maven, "User")
$path = [Environment]::GetEnvironmentVariable("Path", "User")
[Environment]::SetEnvironmentVariable("Path", "$path;$maven\bin", "User")
```

#### 설치 확인

터미널(macOS) 또는 PowerShell(Windows)에서 버전을 출력하여 설치를 확인한다.

```bash
mvn -v
```

다음과 같이 Maven 버전과 함께 **Java version**에 앞에서 설치한 JDK 버전(`25.x.x`)이 출력되면 정상적으로 설치된 것이다.

```
Apache Maven 3.9.x (xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx)
Maven home: /Users/사용자/.sdkman/candidates/maven/current
Java version: 25.0.x, vendor: Eclipse Adoptium, runtime: /Users/사용자/.sdkman/candidates/java/25.0.x-tem
Default locale: ko_KR, platform encoding: UTF-8
OS name: "mac os x", version: "26.x", arch: "aarch64", family: "mac"
```

`Java version`에 다른 버전의 JDK가 출력되면 `JAVA_HOME` 설정을 다시 확인한다.

## Spring Initializr를 이용한 스프링 부트 프로젝트 생성 및 실행

**Spring Initializr**는 스프링 부트 프로젝트의 기본 뼈대를 만들어 주는 도구이다.
빌드 도구, Java 버전, 필요한 라이브러리(의존성)를 선택하면 빌드 설정 파일, 실행 클래스, 폴더 구조가 갖춰진 프로젝트를 생성해 준다.
웹 사이트([https://start.spring.io](https://start.spring.io))에서 사용할 수도 있고, VS Code의 Spring Initializr 확장 프로그램으로 사용할 수도 있다.

이번 실습에서는 `/hello` 요청에 문자열로 응답하는 간단한 웹 애플리케이션을 만들어 실행한다.

### 실습 폴더 준비

실습 프로젝트를 저장할 폴더를 만든다.

- macOS: 홈 폴더 아래 `workspace` 폴더 (`~/workspace`)
- Windows 11: `C:\workspace` 폴더

```bash
# macOS
mkdir -p ~/workspace
```

```powershell
# Windows (PowerShell)
mkdir C:\workspace
```

### 프로젝트 생성

다음 두 가지 방법 중 하나로 프로젝트를 생성한다. 두 방법 모두 같은 프로젝트가 만들어진다.

#### 방법 1: 웹 사이트에서 생성

1. 웹 브라우저에서 [https://start.spring.io](https://start.spring.io) 에 접속한다.
2. 다음과 같이 프로젝트 정보를 설정한다.

   | 항목 | 설정 값 | 설명 |
   | --- | --- | --- |
   | **Project** | `Gradle - Groovy` | 빌드 도구. Maven을 사용하려면 `Maven`을 선택한다. |
   | **Language** | `Java` | 프로그래밍 언어 |
   | **Spring Boot** | `4.1.x` | `SNAPSHOT`, `M1`처럼 꼬리표가 붙지 않은 정식 버전(기본 선택값)을 선택한다. |
   | **Group** | `com.example` | 조직이나 회사를 나타내는 이름. 보통 도메인을 거꾸로 쓴다. |
   | **Artifact** | `hello` | 프로젝트 이름. 빌드 결과 파일(`.jar`)의 이름에 사용된다. |
   | **Name** | `hello` | 애플리케이션 이름. 실행 클래스 이름(`HelloApplication`)에 사용된다. |
   | **Description** | `Hello Spring Boot` | 프로젝트 설명 |
   | **Package name** | `com.example.hello` | 기본 패키지. Group과 Artifact를 합쳐 자동으로 입력된다. |
   | **Packaging** | `Jar` | 내장 웹 서버를 포함한 실행 가능한 `.jar` 파일로 패키징한다. |
   | **Java** | `25` | 앞에서 설치한 JDK 버전 |

3. 오른쪽 **Dependencies** 영역에서 `ADD DEPENDENCIES...` 버튼을 누르고 `Spring Web`을 검색하여 추가한다.
   - **Spring Web**: Spring MVC 기반의 웹 애플리케이션과 REST API를 만들 때 필요하다. 내장 웹 서버인 Tomcat이 함께 포함된다.
4. 화면 아래의 `GENERATE` 버튼을 누르면 `hello.zip` 파일이 내려받아진다.
5. 내려받은 파일의 압축을 실습 폴더(`~/workspace` 또는 `C:\workspace`)에 푼다.
   압축을 풀면 `hello` 폴더가 생긴다.

> `EXPLORE` 버튼을 누르면 내려받기 전에 생성될 파일의 내용을 미리 볼 수 있다.

#### 방법 2: VS Code에서 생성

VS Code 설치 과정에서 설치한 **Spring Boot Extension Pack**에 포함된 Spring Initializr 확장 프로그램을 사용한다.

1. VS Code를 실행한다.
2. 명령 팔레트를 연다. (macOS `Cmd + Shift + P`, Windows `Ctrl + Shift + P`)
3. `spring initializr`를 입력하고 **Spring Initializr: Create a Gradle Project...** 를 선택한다.
   Maven을 사용하려면 **Spring Initializr: Create a Maven Project...** 를 선택한다.
4. 이어서 나타나는 입력 창에서 다음과 같이 선택한다. 값을 입력한 후에는 `Enter` 키를 누른다.

   | 단계 | 설정 값 |
   | --- | --- |
   | Specify Spring Boot version | `4.1.x` (꼬리표가 없는 정식 버전) |
   | Specify project language | `Java` |
   | Input Group Id | `com.example` |
   | Input Artifact Id | `hello` |
   | Specify packaging type | `Jar` |
   | Specify Java version | `25` |
   | Choose dependencies | `Spring Web` 선택 후 `Selected 1 dependency` 선택 |

5. 프로젝트를 저장할 폴더로 실습 폴더(`~/workspace` 또는 `C:\workspace`)를 선택하고 `Generate into this folder` 버튼을 누른다.
6. 오른쪽 아래에 알림이 뜨면 `Open` 버튼을 눌러 생성된 프로젝트를 연다.

### VS Code로 프로젝트 열기

방법 1로 프로젝트를 생성했다면 VS Code로 프로젝트 폴더를 연다.
터미널(macOS) 또는 PowerShell(Windows)에서 다음 명령을 실행한다.

```bash
# macOS
cd ~/workspace/hello
code .
```

```powershell
# Windows (PowerShell)
cd C:\workspace\hello
code .
```

`File > Open Folder...` 메뉴에서 `hello` 폴더를 선택해도 된다.

> 폴더를 처음 열면 "이 폴더에 있는 파일의 작성자를 신뢰합니까?"라는 창이 뜬다. `예, 작성자를 신뢰합니다`를 선택한다.

프로젝트를 열면 Java 확장 프로그램이 Gradle 빌드 설정을 읽고 필요한 라이브러리를 내려받는다.
처음에는 시간이 조금 걸리며, 오른쪽 아래 상태 표시줄에 진행 상황이 표시된다.
작업이 끝나면 왼쪽 탐색기 아래에 **JAVA PROJECTS** 영역이 나타난다.

### 컨트롤러 작성

`/hello` 요청을 처리할 클래스를 만든다.

1. 탐색기에서 `src/main/java/com/example/hello` 폴더를 마우스 오른쪽 버튼으로 클릭하고 `New File...`을 선택한다.
2. 파일 이름으로 `HelloController.java`를 입력한다.
3. 다음과 같이 코드를 작성한다.

```java
package com.example.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello, Spring Boot!";
  }
}
```

- `@RestController`: 이 클래스가 웹 요청을 처리하는 컨트롤러이며, 메서드의 리턴 값을 응답 본문으로 그대로 보낸다는 뜻이다.
- `@GetMapping("/hello")`: `GET /hello` 요청이 들어오면 `hello()` 메서드를 호출한다.

> 애노테이션의 자세한 의미는 4장 "REST API 기초"에서 다룬다. 지금은 프로젝트가 정상적으로 실행되는지 확인하는 데 집중한다.

### 애플리케이션 실행

다음 두 가지 방법 중 하나로 애플리케이션을 실행한다.

#### 방법 1: 명령으로 실행

프로젝트 폴더(`hello`)에서 Gradle Wrapper로 애플리케이션을 실행한다.
VS Code에서는 `Terminal > New Terminal` 메뉴로 프로젝트 폴더가 열린 터미널을 사용할 수 있다.

```bash
# macOS
./gradlew bootRun
```

```powershell
# Windows (PowerShell)
.\gradlew bootRun
```

Maven 프로젝트로 생성했다면 다음 명령을 사용한다.

```bash
# macOS
./mvnw spring-boot:run
```

```powershell
# Windows (PowerShell)
.\mvnw spring-boot:run
```

> `gradlew`, `mvnw`는 프로젝트에 포함된 Wrapper 스크립트이다. 처음 실행할 때 프로젝트에 지정된 버전의 Gradle 또는 Maven을 자동으로 내려받아 사용한다.
> 그래서 팀원 모두가 같은 버전의 빌드 도구를 사용하게 된다.

#### 방법 2: VS Code에서 실행

1. 탐색기에서 `src/main/java/com/example/hello/HelloApplication.java` 파일을 연다.
2. `main()` 메서드 위에 표시되는 `Run` 링크를 클릭한다.

또는 왼쪽 활동 막대의 **Spring Boot Dashboard** 아이콘을 클릭하고, **APPS** 목록에서 `hello` 옆의 실행(▶) 버튼을 눌러도 된다.

#### 실행 로그 확인

실행에 성공하면 로그 마지막 부분에 다음과 같은 내용이 출력된다.

```
... o.s.boot.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
... com.example.hello.HelloApplication : Started HelloApplication in 1.234 seconds (process running for 1.5)
```

- `Tomcat started on port 8080`: 내장 웹 서버(Tomcat)가 8080번 포트에서 요청을 기다리고 있다.
- `Started HelloApplication`: 스프링 부트 애플리케이션이 정상적으로 시작되었다.

> Gradle로 실행하면 진행 표시가 `80% EXECUTING`에서 멈춘 것처럼 보인다. 애플리케이션이 실행 중이라는 뜻이므로 정상이다.

### 실행 확인

웹 브라우저에서 [http://localhost:8080/hello](http://localhost:8080/hello) 에 접속한다.
다음과 같이 출력되면 성공이다.

```
Hello, Spring Boot!
```

터미널에서 `curl` 명령으로 확인할 수도 있다. 애플리케이션을 실행 중인 터미널이 아닌 새 터미널에서 실행한다.

```bash
curl http://localhost:8080/hello
```

> Windows PowerShell에서는 `curl`이 다른 명령의 별칭으로 동작할 수 있으므로 `curl.exe`로 입력한다.

### 애플리케이션 종료

- 명령으로 실행한 경우: 실행 중인 터미널에서 `Ctrl + C`를 누른다.
- VS Code에서 실행한 경우: 화면 위쪽에 표시되는 디버그 도구 막대의 정지(■) 버튼을 누른다.

### 실행 가능한 jar 파일 만들기

스프링 부트 애플리케이션은 내장 웹 서버를 포함한 하나의 `.jar` 파일로 패키징하여 `java` 명령으로 바로 실행할 수 있다.

```bash
# macOS
./gradlew build
java -jar build/libs/hello-0.0.1-SNAPSHOT.jar
```

```powershell
# Windows (PowerShell)
.\gradlew build
java -jar build\libs\hello-0.0.1-SNAPSHOT.jar
```

Maven 프로젝트는 `./mvnw package` 명령으로 빌드하며, `.jar` 파일은 `target` 폴더에 생성된다.

> `build/libs` 폴더에는 `hello-0.0.1-SNAPSHOT-plain.jar` 파일도 함께 생성된다. 이 파일은 라이브러리가 포함되지 않은 파일이므로 실행할 수 없다.

### 문제 해결

**`Port 8080 was already in use` 오류가 발생하는 경우**

다른 프로그램(또는 이전에 실행한 애플리케이션)이 8080번 포트를 사용하고 있다는 뜻이다.
이전에 실행한 애플리케이션을 종료하거나, `src/main/resources/application.properties` 파일에 다음 설정을 추가하여 다른 포트를 사용한다.

```properties
server.port=8081
```

**`Unsupported class file major version` 또는 Java 버전 관련 오류가 발생하는 경우**

프로젝트의 Java 버전과 실행하는 JDK 버전이 맞지 않는다는 뜻이다.
`java -version`과 `JAVA_HOME`이 Java 25를 가리키는지 확인한다.
VS Code에서 실행하는 경우 JDK 설정을 바꾼 후 VS Code를 다시 시작한다.

## Spring Boot 프로젝트 구조 및 주요 파일의 이해

앞에서 Spring Initializr로 생성한 `hello` 프로젝트의 폴더 구조와 주요 파일의 역할을 살펴본다.
스프링 부트 프로젝트는 대부분 같은 구조를 따르므로, 한 번 익혀 두면 다른 프로젝트도 쉽게 파악할 수 있다.

### 전체 폴더 구조

Gradle 프로젝트로 생성한 `hello` 프로젝트의 구조는 다음과 같다.

```
hello
├── .gitattributes
├── .gitignore
├── HELP.md
├── build.gradle                          ← 빌드 설정 파일
├── settings.gradle                       ← 프로젝트 이름 설정 파일
├── gradlew                               ← Gradle Wrapper 실행 스크립트 (macOS/Linux)
├── gradlew.bat                           ← Gradle Wrapper 실행 스크립트 (Windows)
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties     ← 사용할 Gradle 버전 설정
└── src
    ├── main
    │   ├── java
    │   │   └── com/example/hello
    │   │       ├── HelloApplication.java ← 애플리케이션 실행 클래스
    │   │       └── HelloController.java  ← 앞에서 직접 작성한 클래스
    │   └── resources
    │       ├── application.properties    ← 애플리케이션 설정 파일
    │       ├── static                    ← 정적 파일(HTML, CSS, JS, 이미지)
    │       └── templates                 ← 화면 템플릿 파일(Thymeleaf 등)
    └── test
        └── java
            └── com/example/hello
                └── HelloApplicationTests.java ← 테스트 클래스
```

> `.`으로 시작하는 파일은 숨김 파일이다. macOS Finder에서는 `Cmd + Shift + .`를 누르면 보인다. VS Code 탐색기에서는 기본으로 보인다.

각 파일과 폴더의 역할은 다음과 같다.

| 파일/폴더 | 역할 |
| --- | --- |
| `build.gradle` | 사용할 플러그인, Java 버전, 의존 라이브러리 등 빌드 방법을 정의한다. |
| `settings.gradle` | 프로젝트 이름을 정의한다. 여러 하위 프로젝트로 구성할 때 하위 프로젝트 목록도 여기에 정의한다. |
| `gradlew`, `gradlew.bat`, `gradle/wrapper/` | Gradle Wrapper. Gradle을 설치하지 않아도 지정된 버전의 Gradle을 내려받아 빌드한다. |
| `src/main/java` | 애플리케이션의 Java 소스 파일을 둔다. |
| `src/main/resources` | 설정 파일, 정적 파일, 템플릿 파일처럼 Java 소스가 아닌 파일을 둔다. |
| `src/test/java` | 테스트 코드를 둔다. 빌드 결과(`.jar`)에는 포함되지 않는다. |
| `.gitignore` | Git으로 관리하지 않을 파일(빌드 결과, 편집기 설정 등)의 목록이다. |
| `.gitattributes` | Git이 파일을 다루는 방식(줄바꿈 문자 등)을 지정한다. |
| `HELP.md` | 선택한 의존성과 관련된 공식 문서 링크를 모아 둔 안내 파일이다. 지워도 된다. |

> 프로젝트를 빌드하거나 실행하면 `build` 폴더와 `.gradle` 폴더가 생긴다.
> `build` 폴더에는 컴파일된 클래스 파일과 `.jar` 파일이 저장되고, `.gradle` 폴더에는 Gradle의 작업 기록이 저장된다.
> 두 폴더 모두 언제든 다시 만들 수 있으므로 `.gitignore`에 등록되어 Git으로 관리하지 않는다.

`src/main`과 `src/test`로 나누고, 그 아래를 다시 `java`와 `resources`로 나누는 구조는 **Maven 표준 디렉터리 구조**라고 한다.
Maven과 Gradle 모두 이 구조를 기본으로 사용한다.

### settings.gradle

프로젝트의 이름을 정의한다. Spring Initializr에서 입력한 **Artifact** 값이 사용된다.

```groovy
rootProject.name = 'hello'
```

### build.gradle

프로젝트를 어떻게 빌드할지 정의하는 가장 중요한 파일이다.

```groovy
plugins {
  id 'java'
  id 'org.springframework.boot' version '4.1.1'
  id 'io.spring.dependency-management' version '1.1.7'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
description = 'Hello Spring Boot'

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(25)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-webmvc'
  testImplementation 'org.springframework.boot:spring-boot-starter-webmvc-test'
  testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

tasks.named('test') {
  useJUnitPlatform()
}
```

> 생성 시점의 Spring Boot 버전에 따라 버전 번호나 일부 내용이 다를 수 있다.

**① plugins**

빌드에 사용할 Gradle 플러그인을 지정한다.

| 플러그인 | 역할 |
| --- | --- |
| `java` | Java 소스를 컴파일하고 테스트하고 `.jar`로 묶는 기본 작업(task)을 추가한다. |
| `org.springframework.boot` | 스프링 부트 전용 작업을 추가한다. 앞에서 사용한 `bootRun`(실행), `bootJar`(실행 가능한 jar 생성)가 이 플러그인이 제공하는 작업이다. |
| `io.spring.dependency-management` | 스프링 부트 버전에 맞는 라이브러리 버전을 자동으로 지정해 준다. |

**② group, version, description**

프로젝트를 식별하는 정보이다. Spring Initializr에서 입력한 **Group**, **Description** 값이 사용된다.
`version`의 `SNAPSHOT`은 아직 개발 중인 버전이라는 뜻이다.
빌드한 `.jar` 파일 이름은 `프로젝트이름-버전.jar`(`hello-0.0.1-SNAPSHOT.jar`) 형식으로 만들어진다.

**③ java toolchain**

이 프로젝트를 컴파일하고 실행할 때 사용할 Java 버전을 지정한다.
Gradle은 설치된 JDK 중에서 Java 25를 찾아 사용하며, 찾지 못하면 빌드 오류가 발생한다.

**④ repositories**

의존 라이브러리를 내려받을 저장소를 지정한다.
`mavenCentral()`은 Java 오픈 소스 라이브러리가 모여 있는 공개 저장소인 **Maven Central**([https://central.sonatype.com](https://central.sonatype.com))을 뜻한다.

**⑤ dependencies**

프로젝트에서 사용할 라이브러리(의존성)를 지정한다. 형식은 `설정 '그룹:이름:버전'`이다.

| 설정 | 의미 |
| --- | --- |
| `implementation` | 컴파일과 실행에 모두 필요한 라이브러리 |
| `testImplementation` | 테스트 코드를 컴파일하고 실행할 때만 필요한 라이브러리 |
| `testRuntimeOnly` | 테스트를 실행할 때만 필요한 라이브러리 |

이번 프로젝트에 추가된 라이브러리는 다음과 같다.

- `spring-boot-starter-webmvc`: Spring Initializr에서 선택한 **Spring Web**에 해당한다. Spring MVC, 내장 Tomcat, JSON 변환 라이브러리 등 웹 애플리케이션에 필요한 라이브러리를 한꺼번에 가져온다.
- `spring-boot-starter-webmvc-test`: Spring MVC 애플리케이션을 테스트하는 데 필요한 라이브러리(JUnit, AssertJ, Mockito 등)를 한꺼번에 가져온다.
- `junit-platform-launcher`: Gradle이 JUnit 테스트를 실행할 때 사용한다.

이처럼 관련 라이브러리를 묶어 한 번에 추가할 수 있게 만든 것을 **스타터**(Starter)라고 한다.
스타터 이름은 `spring-boot-starter-기술이름` 형식이며, 테스트용 스타터는 뒤에 `-test`가 붙는다.

라이브러리에 버전을 적지 않은 것에 주목하자.
`io.spring.dependency-management` 플러그인이 스프링 부트 버전(`4.1.1`)에 맞춰 검증된 버전을 자동으로 지정해 주기 때문이다.
덕분에 라이브러리 사이의 버전 충돌을 신경 쓰지 않아도 된다.

> 스타터와 자동 구성(Auto Configuration)은 2장 "Spring Boot 개요"에서 자세히 다룬다.
> Spring Boot 3.x까지는 Spring Web의 스타터 이름이 `spring-boot-starter-web`이었다. 인터넷의 예제 코드에서 이 이름을 보면 같은 역할의 스타터로 이해하면 된다.

**⑥ tasks.named('test')**

`test` 작업이 JUnit 5 이상의 테스트 플랫폼(JUnit Platform)을 사용하도록 설정한다.

### Gradle Wrapper

`gradlew`, `gradlew.bat`, `gradle/wrapper` 폴더를 합쳐 **Gradle Wrapper**라고 한다.
`gradle/wrapper/gradle-wrapper.properties` 파일에 이 프로젝트가 사용할 Gradle 버전이 지정되어 있다.

```properties
distributionUrl=https\://services.gradle.org/distributions/gradle-9.x.x-bin.zip
```

`./gradlew` 명령을 처음 실행하면 이 주소에서 Gradle을 내려받아 홈 폴더의 `.gradle/wrapper/dists` 폴더에 저장하고, 이후에는 저장된 Gradle을 사용한다.
그래서 컴퓨터에 설치된 Gradle 버전과 관계없이 모든 개발자가 같은 버전의 Gradle로 빌드하게 된다.
Gradle Wrapper 파일은 Git 저장소에 함께 올려야 한다.

> 앞에서 설치한 `gradle` 명령 대신 프로젝트에서는 항상 `./gradlew`(Windows에서는 `.\gradlew`)를 사용하는 것이 좋다.

### HelloApplication.java

애플리케이션을 시작하는 클래스이다. 클래스 이름은 Spring Initializr에서 입력한 **Name** 값 뒤에 `Application`을 붙여 만들어진다.

```java
package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloApplication {

  public static void main(String[] args) {
    SpringApplication.run(HelloApplication.class, args);
  }

}
```

- `main()` 메서드: 일반 Java 프로그램처럼 `main()` 메서드에서 실행이 시작된다.
- `SpringApplication.run()`: 스프링 컨테이너를 만들고, 필요한 객체를 생성하여 연결하고, 내장 웹 서버(Tomcat)를 실행한다.
- `@SpringBootApplication`: 다음 세 가지 애노테이션을 합친 것이다.

| 애노테이션 | 역할 |
| --- | --- |
| `@SpringBootConfiguration` | 이 클래스가 스프링 설정 클래스임을 표시한다. |
| `@EnableAutoConfiguration` | 추가된 라이브러리를 보고 필요한 설정을 자동으로 구성한다. 예를 들어 `spring-boot-starter-webmvc`가 있으면 Spring MVC와 Tomcat을 자동으로 설정한다. |
| `@ComponentScan` | 이 클래스가 있는 패키지(`com.example.hello`)와 그 하위 패키지에서 `@RestController`, `@Service` 등이 붙은 클래스를 찾아 객체로 생성한다. |

앞에서 작성한 `HelloController`가 별도의 설정 없이 동작한 것은 `@ComponentScan` 덕분이다.
`HelloController`가 `HelloApplication`과 같은 패키지에 있어서 자동으로 발견되고 객체로 생성된 것이다.

> **주의**: 앞으로 만드는 클래스는 반드시 `com.example.hello` 패키지 또는 그 하위 패키지(예: `com.example.hello.board`)에 두어야 한다.
> 다른 패키지(예: `com.example.board`)에 두면 스프링이 찾지 못해서 동작하지 않는다.

> 스프링 컨테이너와 객체(Bean)의 생성·연결 원리는 3장 "IoC와 DI"에서 자세히 다룬다.

### application.properties

애플리케이션의 설정 값을 `이름=값` 형식으로 적는 파일이다. 처음에는 애플리케이션 이름만 들어 있다.

```properties
spring.application.name=hello
```

스프링 부트는 대부분의 설정에 기본값을 가지고 있으며, 기본값을 바꾸고 싶을 때만 이 파일에 적는다.
예를 들어 다음과 같이 설정할 수 있다.

```properties
# 웹 서버 포트 번호 변경 (기본값: 8080)
server.port=8081

# 특정 패키지의 로그 출력 수준 변경
logging.level.com.example.hello=debug
```

> 같은 설정을 YAML 형식의 `application.yml` 파일로 작성할 수도 있다. 이 과정에서는 `application.properties`를 사용한다.
> 설정할 수 있는 항목의 목록은 [Common Application Properties](https://docs.spring.io/spring-boot/appendix/application-properties/index.html) 문서에서 확인할 수 있다.

### static 폴더와 templates 폴더

`src/main/resources` 아래의 두 폴더는 웹 애플리케이션에서 사용하는 파일을 두는 곳이다.

| 폴더 | 용도 | 접근 방법 |
| --- | --- | --- |
| `static` | HTML, CSS, JavaScript, 이미지처럼 내용이 바뀌지 않는 정적 파일 | 파일 경로로 바로 접근한다. (예: `static/index.html` → `http://localhost:8080/index.html`) |
| `templates` | 요청마다 데이터를 채워 화면을 만드는 템플릿 파일 | 컨트롤러를 거쳐서 사용한다. 10장 "Thymeleaf 기초"에서 다룬다. |

간단히 확인해 보자. `src/main/resources/static` 폴더에 `index.html` 파일을 만들고 다음과 같이 작성한다.

```html
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Hello</title>
</head>
<body>
  <h1>안녕하세요, 스프링 부트!</h1>
  <a href="/hello">/hello 요청하기</a>
</body>
</html>
```

애플리케이션을 다시 실행하고 [http://localhost:8080](http://localhost:8080) 에 접속하면 `index.html`의 내용이 출력된다.
`static` 폴더의 `index.html`은 첫 화면(웰컴 페이지)으로 사용된다.

### HelloApplicationTests.java

Spring Initializr가 기본으로 만들어 주는 테스트 클래스이다.

```java
package com.example.hello;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloApplicationTests {

  @Test
  void contextLoads() {
  }

}
```

- `@SpringBootTest`: 테스트를 실행할 때 실제 애플리케이션과 똑같이 스프링 컨테이너를 구성한다.
- `contextLoads()`: 내용이 비어 있지만, 스프링 컨테이너가 오류 없이 만들어지는지 확인하는 테스트이다. 설정이 잘못되었거나 객체를 만들 수 없으면 이 테스트가 실패한다.

다음 명령으로 테스트를 실행한다.

```bash
# macOS
./gradlew test
```

```powershell
# Windows (PowerShell)
.\gradlew test
```

`BUILD SUCCESSFUL`이 출력되면 테스트를 통과한 것이다.
테스트 결과 보고서는 `build/reports/tests/test/index.html` 파일을 웹 브라우저로 열어 확인할 수 있다.

> 앞에서 실행한 `./gradlew build` 명령도 빌드 과정에서 테스트를 함께 실행한다. 테스트가 실패하면 `.jar` 파일이 만들어지지 않는다.

### Maven 프로젝트와 비교

Spring Initializr에서 **Project**를 `Maven`으로 선택하면 빌드 관련 파일만 다르고 `src` 폴더의 구조는 같다.

```
hello
├── .mvn
│   └── wrapper
│       └── maven-wrapper.properties      ← 사용할 Maven 버전 설정
├── mvnw                                  ← Maven Wrapper 실행 스크립트 (macOS/Linux)
├── mvnw.cmd                              ← Maven Wrapper 실행 스크립트 (Windows)
├── pom.xml                               ← 빌드 설정 파일
└── src                                   ← Gradle 프로젝트와 동일
```

`pom.xml`은 `build.gradle`과 같은 역할을 XML 형식으로 작성한 파일이다. 주요 부분은 다음과 같다.

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>4.1.1</version>
</parent>

<groupId>com.example</groupId>
<artifactId>hello</artifactId>
<version>0.0.1-SNAPSHOT</version>

<properties>
  <java.version>25</java.version>
</properties>

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>

<build>
  <plugins>
    <plugin>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
  </plugins>
</build>
```

Gradle과 Maven의 주요 항목을 비교하면 다음과 같다.

| 항목 | Gradle | Maven |
| --- | --- | --- |
| 빌드 설정 파일 | `build.gradle` (Groovy 문법) | `pom.xml` (XML 문법) |
| 프로젝트 이름 | `settings.gradle`의 `rootProject.name` | `pom.xml`의 `<artifactId>` |
| Java 버전 | `java { toolchain { ... } }` | `<java.version>` |
| 라이브러리 버전 관리 | `io.spring.dependency-management` 플러그인 | `<parent>`의 `spring-boot-starter-parent` |
| 테스트 전용 라이브러리 | `testImplementation` | `<scope>test</scope>` |
| Wrapper | `gradlew`, `gradle/wrapper/` | `mvnw`, `.mvn/wrapper/` |
| 실행 명령 | `./gradlew bootRun` | `./mvnw spring-boot:run` |
| 빌드 명령 | `./gradlew build` | `./mvnw package` |
| 빌드 결과 폴더 | `build/` (jar는 `build/libs/`) | `target/` |

