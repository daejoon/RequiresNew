# Propagation.REQUIRES_NEW 테스트

`@Transactional(propagation = Propagation.REQUIRES_NEW)`를 사용하는 예제를 찾아보면 대부분
DB에 로그를 추가할때 문제가 발생해도 실제 로직은 정상적으로 Commit 되게 하기 위해서 사용하는 예제이다.

팀에서도 실제로 그 이유로 사용하고 있었는데 이번에 문제가 발생하면서 테스트 코드 및 디버깅을 하게 되었다.

결론만 말하자면 REQUIRES_NEW 안쪽에서 try catch를 하더라도 Commit시 Rollback 예외를 발생시키기 때문에
호출하는 쪽에서 try catch를 걸어주지 않으면 결국 예외가 발생해서 롤백 된다.

호출 하는 곳마다 try catch를 걸기 싫다면 try catch로 감싸는 UserLogTryCatchService를 만들고
주입받은 UserLogService의 메소드를 try catch로 감싸는 것도 방법이다.
