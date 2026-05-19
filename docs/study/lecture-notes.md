# REST API 학습 노트

강의별로 학습한 내용과 커밋 단위를 정리합니다.

## 1강

- 날짜: 2025-09-03
- 프로젝트: p 14526
- 주제: 프로젝트 세팅

### 학습 내용

- REST API 학습을 위한 Spring Boot 프로젝트를 세팅했습니다.

## 2강

- 날짜: 2025-09-03
- 프로젝트: p 14526
- 주제: UI 구현 방식에 따른 백엔드 구현 형태 차이

### 학습 내용

UI를 어떤 방식으로 구현하느냐에 따라 백엔드의 응답 형태가 달라집니다.

React 같은 프론트엔드 라이브러리를 사용하는 프로젝트에서는 백엔드가 주로 JSON 데이터를 응답합니다.

```json
{
  "id": 1,
  "title": "제목 1",
  "content": "내용 1"
}
```

Thymeleaf 프로젝트에서는 백엔드가 HTML 화면을 직접 렌더링해서 응답할 수 있습니다.

```html
<h1>번호 : 1</h1>

<h2>제목</h2>
<div>제목 1</div>

<h2>내용</h2>
<div style="white-space: pre-line;">내용 1</div>
```

### 정리

- React 방식: 백엔드는 JSON API를 제공하고, 화면은 프론트엔드에서 구성합니다.
- Thymeleaf 방식: 백엔드가 HTML View를 렌더링해서 응답합니다.
- REST API 프로젝트에서는 JSON 응답 구조를 명확하게 설계하는 것이 중요합니다.

## 3강

- 날짜: 2025-09-03
- 프로젝트: p 14526
- 주제: post 도메인 작업과 기본 기능 구현

### 1부

- post 도메인 작업
- JPA 적용
- `BaseEntity` 구성
- `InitData` 구성
- 프로파일 설정

### 2부

- post 게시글 데이터 3개 생성
- post 기본 기능 구현

### 3부

- `postComment` 관련 기능 개발
- 댓글 데이터 5개 생성

### 커밋 기록

```text
post 도메인 작업, jpa, baseEntity, initData, 프로파일 설정
post 게시글 데이터 3개 생성, 기본 기능 구현
postComment 관련 기능 개발, 댓글 5개 생성
```

### 추천 커밋 메시지

```text
feat(post): 게시글 도메인과 초기 데이터 구성
feat(post): 게시글 기본 기능 구현
feat(comment): 게시글 댓글 기능 구현
```

## 4강

- 날짜: 2025-09-03
- 프로젝트: p 14526
- 주제: 게시글 조회 API와 REST Controller 적용

### 1부

- 글 다건조회 API 구현
- `PostComment`의 `post` 변수에 `@JsonIgnore` 추가
- 게시글과 댓글의 양방향 관계를 JSON으로 응답할 때 무한 루프가 발생하지 않도록 처리

### 2부

- `@RestController` 적용
- 클래스 레벨에 `@RequestMapping` 추가
- 공통 API 접두어를 컨트롤러 단위에서 관리

### 커밋 기록

```text
api/v1/posts 구현, PostComment의 post 변수에 @JsonIgnore 어노테이션 추가하여 무한루프 발생하지 않도록
@RestController == @Controller + @ResponseBody, 클래스 레벨에 @RequestMapping 추가하여 공통 접두어 추가
```

### 추천 커밋 메시지

```text
feat(post): 게시글 다건 조회 API 구현
fix(comment): 댓글 JSON 응답 무한 루프 방지
feat(post): 게시글 API 공통 경로 적용
docs(study): RestController와 RequestMapping 정리
```

### 추가 정리

- `@JsonIgnore`는 JSON 직렬화 대상에서 특정 필드를 제외할 때 사용합니다.
- JPA 양방향 연관관계에서 양쪽 객체가 서로를 계속 참조하면 JSON 변환 중 무한 루프가 발생할 수 있습니다.
- `@RestController`는 REST API 응답을 만들 때 사용하며, `@Controller`와 `@ResponseBody`를 합친 역할을 합니다.
- 클래스 레벨 `@RequestMapping`은 컨트롤러 안의 API에 공통 URL 접두어를 붙일 때 사용합니다.
