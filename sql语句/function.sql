create or replace function empfun 
return number
is
       tatal number;
begin
  select count(*) into tatal from emp;
  return tatal;
end;
create or replace function salfun(aid in emp.empno%type)
return emp.sal%type 
is 
       getsal emp.sal%type;
       inid emp.empno%type;
begin
  inid := aid;
  select sal into getsal from emp where empno=inid;
  return getsal;
end;
/      
create or replace procedure empdata
is
       cursor empcursor is select empno,sal from emp;
       remp empcursor%rowtype;
       rempno emp.empno%type;
       rsal emp.sal%type;
begin    
     open empcursor;   
     loop
       fetch empcursor into remp;
       exit when empcursor%notfound;
       
       DBMS_OUTPUT.PUT_LINE(remp.rempno);
       DBMS_OUTPUT.PUT_LINE(remp.rsal);
     end loop;  
     close empcursor;
end;
/

create or replace procedure empdata
is
       cursor empcursor is select empno,sal from emp;
       remp empcursor%rowtype;
       rempno emp.empno%type;
       rsal emp.sal%type;
begin    
     for  remp in empcursor
     loop     
       DBMS_OUTPUT.PUT_LINE(remp.empno);
       DBMS_OUTPUT.PUT_LINE(remp.sal);
     end loop;  
     
end;
/

create or replace procedure empdata
is
       cursor empcursor is select empno,sal from emp;
       remp empcursor%rowtype;
       rempno emp.empno%type;
       rsal emp.sal%type;
begin    
     open empcursor;    
     loop 
       exit when  empcursor%notfound;
       fetch  empcursor into  remp;
       DBMS_OUTPUT.PUT_LINE(remp.empno||'fff'||remp.sal);     
     end loop;  
     close   empcursor; 
end;
/
create or replace procedure empda is 
       cursor tmpcur(eno emp.empno%type) is select empno,sal from emp where empno = eno;
       remp tmpcur%rowtype;
begin
       open tmpcur;
       loop
         exit when tmpcur%notfound;
         fetch tmpcur into remp;
         DBMS_OUTPUT.PUT_LINE(remp.empno||'fff'||remp.sal);
       end loop;
       close 
end;

/
create or replace procedure empdata
is
       cursor empcursor(eno emp.empno%type) is select empno,sal from emp where empno = eno;
       remp empcursor%rowtype;
       
begin    
     open empcursor;
     
     loop 
       exit when  empcursor%notfound;
       fetch  empcursor into  remp;
       DBMS_OUTPUT.PUT_LINE(remp.empno||'fff'||remp.sal);     
     end loop;     
end;
/

select * from emp;
select empno,sal from emp where empno = 7369;

create or replace procedure empdata
  2  is
  3         cursor empcursor (eno number)is select empno,sal from emp where empno = eno;
  4         remp empcursor%rowtype;
  7  begin
  8       open empcursor(7369);
  9       loop
 10         exit when  empcursor%notfound;
 11         fetch  empcursor into  remp;
 12         DBMS_OUTPUT.PUT_LINE(remp.empno||'fff'||remp.sal);
 13       end loop;
 14       close   empcursor;
 15  end;
create or replace procedure pcur is 
      type curtype is ref cursor;
      cur1 curtype;
      cur2 curtype;
      res emp%rowtype;
begin
  open cur1 for select * from emp;
  
  loop
    exit when cur1%notfound;
    fetch cur1 into res;
    DBMS_OUTPUT.PUT_LINE(res.empno||'fff'||res.sal);
  end;
  cur2 := cur1;
  loop
    exit when cur2%notfound;
    fetch cur2 into res;
    DBMS_OUTPUT.PUT_LINE(res.empno||'ggg'||res.sal);
  end;
  close cur2;
end;



declare 
  type curtype is ref cursor;
      cur1 curtype;
      cur2 curtype;
create or replace procedure curdata
is
      res emp%rowtype;
begin    
     open cur1 for select sal from emp;    
     loop 
       exit when  cur1%notfound;
       fetch  cur1 into  res;
       DBMS_OUTPUT.PUT_LINE('fff'||res.sal);     
     end loop;  
     close   cur1; 
end;
/

declare 
  type curtype is ref cursor;
      cur1 curtype;
      cur2 curtype;
      
procedure curdata(incur in  curtype)
is
      res emp%rowtype;
begin    
       
     loop 
       exit when  cur1%notfound;
       fetch  incur into  res;
       DBMS_OUTPUT.PUT_LINE('fff'||res.sal);     
     end loop;  
end;
begin
     open cur1 for select * from emp;
     cur2 := cur1; 
       curdata(cur1);
       DBMS_OUTPUT.PUT_LINE('1111111111111111');
       
       curdata(cur2);
end;

/
create or replace function fun1 (str in varchar2)
return varchar2
is
       res varchar2(10);
begin
       res := str||'jjjjj';
       return res;
end;



