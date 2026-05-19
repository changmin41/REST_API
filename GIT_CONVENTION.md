# Git Convention

REST API 학습 프로젝트에서 커밋을 일관되게 남기기 위한 규칙입니다.
커밋 메시지는 나중에 "무엇을 공부했고, 어떤 API 동작이 바뀌었는지" 빠르게 파악할 수 있게 작성합니다.

## Commit Message Format

```text
<type>(<scope>): <subject>
```

예시:

```text
feat(post): 게시글 단건 조회 API 구현
fix(api): 존재하지 않는 게시글 조회 시 404 응답 처리
test(post): 게시글 등록 서비스 테스트 추가
docs(git): 커밋 컨벤션 문서 추가
```

## Type

| Type | 사용 시점 | 예시 |
| --- | --- | --- |
| `feat` | 새로운 API, 기능, 엔드포인트 추가 | `feat(post): 게시글 목록 조회 API 구현` |
| `fix` | 버그 수정, 잘못된 응답/로직 수정 | `fix(post): 게시글 수정 시 작성자 검증 오류 수정` |
| `refactor` | 동작 변경 없이 코드 구조 개선 | `refactor(post): 게시글 응답 DTO 분리` |
| `test` | 테스트 추가/수정 | `test(comment): 댓글 생성 테스트 추가` |
| `docs` | 문서 작성/수정 | `docs(readme): 실행 방법 추가` |
| `chore` | 빌드, 설정, 의존성 외 기타 작업 | `chore(git): gitignore 정리` |
| `build` | Gradle, 의존성, 빌드 설정 변경 | `build(gradle): h2 의존성 추가` |
| `style` | 포맷팅, import 정리 등 로직 없는 변경 | `style(post): 컨트롤러 import 정리` |
| `study` | 강의/학습 진도 정리용 커밋 | `study(lecture): 4강 게시글 API 실습` |

## Scope

`scope`는 변경한 영역을 짧게 씁니다.

권장 scope:

```text
post, comment, member, auth, api, global, db, gradle, docs, git, lecture
```

예시:

```text
feat(comment): 댓글 목록 조회 API 구현
fix(db): 개발 DB 연결 설정 수정
refactor(global): 공통 응답 형식 정리
study(lecture): 5강 댓글 도메인 실습
```

## Subject

- 한글로 작성해도 됩니다.
- 50자 안팎으로 짧게 씁니다.
- 마침표는 붙이지 않습니다.
- "수정함", "작업함"처럼 모호하게 쓰지 말고 변경 내용을 구체적으로 씁니다.

좋은 예:

```text
feat(post): 게시글 생성 API 구현
fix(post): 게시글 조회 실패 시 예외 응답 수정
refactor(api): 컨트롤러 응답 DTO 적용
```

피해야 할 예:

```text
수정
post 작업
에러 해결
이것저것 변경
```

## Commit Size

커밋 하나에는 하나의 목적만 담습니다.

좋은 단위:

- 게시글 등록 API 추가
- 댓글 엔티티 추가
- H2 console 설정 오류 수정
- 테스트 코드 추가
- 문서 수정

나누는 것이 좋은 경우:

```text
feat(post): 게시글 등록 API 구현
test(post): 게시글 등록 서비스 테스트 추가
docs(api): 게시글 API 사용 예시 추가
```

## Branch Convention

브랜치는 다음 형식을 권장합니다.

```text
<type>/<short-description>
```

예시:

```text
feature/post-api
feature/comment-api
fix/h2-console-error
docs/git-convention
study/lecture-04
```

## Commit Before Checklist

커밋하기 전에 아래 순서로 확인합니다.

```powershell
git status --short
git diff --check
.\gradlew.bat test
```

확인 후 필요한 파일만 선택해서 커밋합니다.

```powershell
git add src/main/java/com/back/domain/post/post/controller/ApiV1PostController.java
git commit -m "feat(post): 게시글 단건 조회 API 구현"
```

## Do Not Commit

아래 파일/폴더는 특별한 이유가 없으면 커밋하지 않습니다.

```text
.idea/
.gradle/
build/
out/
*.iml
db_dev.mv.db
```

개발용 DB 파일이나 IDE 설정은 다른 사람의 환경과 충돌할 수 있으므로 커밋 대상에서 제외합니다.

## REST API Commit Examples

```text
feat(post): 게시글 목록 조회 API 구현
feat(post): 게시글 생성 API 구현
feat(comment): 게시글 댓글 등록 API 구현
fix(api): 잘못된 요청 값 검증 응답 수정
refactor(post): 게시글 엔티티 생성 메서드 분리
test(post): 게시글 수정 서비스 테스트 추가
docs(api): 게시글 API 요청 예시 추가
build(gradle): validation 의존성 추가
study(lecture): 6강 REST API 응답 구조 실습
```
