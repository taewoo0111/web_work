package com.example.spring11;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.spring11.dto.EmpDeptDto;
import com.example.spring11.dto.GalleryDto;
import com.example.spring11.entity.Gallery;
import com.example.spring11.entity.Member;
import com.example.spring11.repository.EmpRepository;
import com.example.spring11.repository.GalleryRepository;
import com.example.spring11.repository.MemberRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

@SpringBootApplication
public class Spring11JpaApplication {
	
	@Autowired MemberRepository memberRepo;
	@Autowired GalleryRepository galleryRepo;
	@Autowired EntityManagerFactory emf;
	@Autowired EmpRepository empRepo;
	
	@PostConstruct
	public void initEmpDept() {
		/*
		 * emp, dept 셈플 데이터를 JPQL 을 이용해서 넣어주기
		 */

		// EntityManager 객체 얻어내서
		EntityManager em = emf.createEntityManager();
		// 하나의 트랜젝션을 시작한다
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			List<String> queries = new ArrayList<>();

			queries.add("INSERT INTO DEPT VALUES (10,'ACCOUNTING','NEW YORK');");
			queries.add("INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS');");
			queries.add("INSERT INTO DEPT VALUES (30,'SALES','CHICAGO');");
			queries.add("INSERT INTO DEPT VALUES (40,'OPERATIONS','BOSTON');");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7369,'SMITH','CLERK',7902,parsedatetime('17-12-1980','dd-MM-yyyy'),800,NULL,20);");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7499,'ALLEN','SALESMAN',7698,parsedatetime('20-02-1981','dd-MM-yyyy'),1600,300,30);");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7521,'WARD','SALESMAN',7698,parsedatetime('22-02-1981','dd-MM-yyyy'),1250,500,30);");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7566,'JONES','MANAGER',7839,parsedatetime('02-04-1981','dd-MM-yyyy'),2975,NULL,20);");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7654,'MARTIN','SALESMAN',7698,parsedatetime('28-09-1981','dd-MM-yyyy'),1250,1400,30);");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7698,'BLAKE','MANAGER',7839,parsedatetime('01-05-1981','dd-MM-yyyy'),2850,NULL,30);");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7782,'CLARK','MANAGER',7839,parsedatetime('09-06-1981','dd-MM-yyyy'),2450,NULL,10);");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7788,'SCOTT','ANALYST',7566,parsedatetime('13-07-1987','dd-MM-yyyy'),3000,NULL,20);");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7839,'KING','PRESIDENT',NULL,parsedatetime('17-11-1981','dd-MM-yyyy'),5000,NULL,10);");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7844,'TURNER','SALESMAN',7698,parsedatetime('08-09-1981','dd-MM-yyyy'),1500,0,30);");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7876,'ADAMS','CLERK',7788,parsedatetime('13-07-1987','dd-MM-yyyy'),1100,NULL,20);");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7900,'JAMES','CLERK',7698,parsedatetime('03-12-1981','dd-MM-yyyy'),950,NULL,30);");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7902,'FORD','ANALYST',7566,parsedatetime('03-12-1981','dd-MM-yyyy'),3000,NULL,20);");
			queries.add(
					"INSERT INTO EMP (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (7934,'MILLER','CLERK',7782,parsedatetime('23-01-1982','dd-MM-yyyy'),1300,NULL,10);");

			// 반복문 돌면서 실행할 쿼리를 얻어내서
			for (String query : queries) {
				// 직접 실행한다
				em.createNativeQuery(query).executeUpdate();
			}

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		// 모든 사원의 정보를 부서정보 포함해서 읽어온 다음 콘솔창에 출력
		empRepo.findAll().stream().map(EmpDeptDto::toDto).forEach(System.out::println);
	}
	
	
	@PostConstruct
	public void galleryTest() {
		Gallery g1 = Gallery.builder().writer("김태우1").title("테스트1").createdAt(new Date()).build();
		Gallery g2 = Gallery.builder().writer("김태우2").title("테스트2").createdAt(new Date()).build();
		Gallery g3 = Gallery.builder().writer("김태우3").title("테스트3").createdAt(new Date()).build();
		galleryRepo.save(g1);
		galleryRepo.save(g2);
		galleryRepo.save(g3);
		
		List<GalleryDto> list = galleryRepo.findAll().stream().map(GalleryDto::toDto).toList();
		//list.stream().forEach(item -> System.out.println(item));
		list.stream().forEach(System.out::println);
		
		EntityManager em2 = emf.createEntityManager();
		EntityTransaction tx = em2.getTransaction();
		tx.begin();
		try {
			Gallery g4 = Gallery.builder().writer("김태우4").title("테스트4").build();
			// EntityManager 객체를 이용해서 Gallery 객체를 영속화(영구저장, DB에 저장) 하겠다는 의미
			em2.persist(g4);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em2.close();
		}
	}
	
	@PostConstruct
	public void memberTest() {
		Member m1 = Member.builder().name("김구라").addr("노량진").build();
		Member m2 = Member.builder().name("해골").addr("행신동").build();
		Member m3 = Member.builder().name("원숭이").addr("상도동").build();
		memberRepo.save(m1);
		memberRepo.save(m2);
		memberRepo.save(m3);
	}

	public static void main(String[] args) {
		SpringApplication.run(Spring11JpaApplication.class, args);
	}
}
