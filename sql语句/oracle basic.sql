create table emp_dept(
       deptno number(4) not null,
       dname varchar2(15) ,
       loc varchar2(15)
);
select * from dept;
alter table EMP_DEPT add constraint EMP_DEPT primary key(deptno);
insert into EMP_DEPT(deptno,Dname,LOC) select dept.deptno,dept.dname,dept.loc from dept;
select * from emp_dept;
select count(*) from emp_dept;
select * from emp where ename = 'Smith';
select * from emp where empno in (7844,7839,0001);
select * from emp where mgr is null;
select * from emp ;
select * from (select a.* ,rownum  rn from (select * from emp) a where rownum<5) where rn >2;
create table goods(
       goodsId char(8) primary key,
       goodsName varchar2(30)
);
create table customer(
       customerId char(8) primary key,
       customerName varchar2(30)
);
create table purchase(
       goodsId char(8) references goods(goodsId),
       customerId char(8) references customer(customerId),
       nums number(10)
);
select * from stu;
select * from tmptest;
create table tmptest(country varchar2(20),city varchar2(20));
insert into tmptest(country,city) values ('china','beijing');
insert into tmptest(country,city) values ('usa','ny');
create procedure ptmptest(thecountry varchar2,newcity varchar2) is 
begin
  update tmptest set city = newcity where country=thecountry;
end;
/
create or replace function getCity(ucountry varchar2)
return varchar2 is
cityRes varchar2(20);
begin
  select city into cityRes from tmptest where country=ucountry;
  return cityRes;
end;
/
create or replace package sp_package is
       procedure update_city(ucountry varchar2,newcity varchar2);
       
end;
 
create or replace package body sp_package is
       procedure update_city(ucountry varchar2,newcity varchar2) is 
         begin 
           update tmptest set city=newcity where country = ucountry;
           commit;
         end;
       
end;
/
set serveroutput on;
declare 
    c_tax_rate number(3,2) := 0.02;
    v_city tmptest.city%type;
    begin
      select city into v_city from tmptest where country='china';
      dbms_output.put_line(v_city);
      
end;
/

declare
      type sp_tmp_cursor is ref cursor;
      sp sp_tmp_cursor;
      vcountry tmptest.country%type;
      vcity tmptest.city%type;
      begin
        open sp for select country,city into vcountry,vcity from tmptest;
       
      Loop 
        fetch sp into vcountry,vcity;
        exit when sp%notfound;
        dbms_output.put_line(vcity||'bbbb'||vcountry);
      end Loop;
      end;
/

create or replace trigger tmptrigger before insert or update or delete
on tmptest
begin 
 if(to_char(sysdate,'DY')='星期日') then
 AISE_APPLICATION_ERROR(-20600,'不能在周末修改表');                     
end;
/
select * from user_sequences;
select user from dual;
select * from test;
select 1 from test;
delete from test t1 where t1.rowid not in 
       (select min(t2.rowid) from test t2 group by username);
insert into test(username) values('tang');
delete from test t1 where t1.rowid > 
       (select min(rowid) from test t2 where t1.username=t2.username);
