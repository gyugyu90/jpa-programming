# jpa-programming

#### JPA를 사용하는 이유
- SQL <-> OOP 간의 패러다임 불일치를 해소할 수 있다.
  - 상속, 연관관계, 그래프 탐색, 동일성 비교 등
- 코드양이 줄어들기 때문에 생산성이 높아지고, 유지보수 하기 편하다.
- 데이터베이스에 대한 추상화가 되어 있어서 DB 변경에도 용이하다.

#### EntityManager
- EntityManagerFactory는 JPA를 위한 기반 객체를 만들고 DB 커넥션 풀도 생성하므로 
애플리케이션 전체에서 한 번만 생성하고 공유해서 사용하는 것이 좋다.  
- EntityManager를 통해서 엔티티를 DB에 CRUD할 수 있다.

```java
class Ex {
    public void transactionExample(EntityMananger em) {
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
        }
        
    }
}
```

