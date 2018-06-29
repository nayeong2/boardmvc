-- �Խ��� �޴�
SELECT b.bcode, b.bname, b.btype, c.ccode, c.cname,
		case
			when b.btype= 5 then 'reboard'
			when b.btype= 6 then 'album'
			when b.btype= 7 then 'bbs'
			else 'board'
		end control
FROM board_list b, category c
WHERE b.ccode= c.ccode
ORDER BY b.bcode asc;

-- ��ȭ�鿡 20���� ��
-- ���� pg= 2 ����
-- bcode= 3
-- ���� : ��:��:��
-- ����x : 00.00.00

select b.*
from (
	select rownum rn, a.*
	from (
		select	b.seq, b.id, b.name, b.email, b.subject, b.content, b.hit, b.bcode, 
				r.resq, r.ref, r.lev, r.step, r.pseq, r.reply,
				case
					when to_char(logtime, 'yymmdd')= to_char(sysdate, 'yymmdd')
					then to_char(logtime, 'hh24:mi:ss')
					else to_char(logtime, 'yy.mm.dd')
				end logtime
		from board b, reboard r
		where b.seq= r.seq
		and b.bcode= 3
		order by seq desc
		) a
	where rownum <=40;
	) b
where b.rn>20;

-- ������ ����
select count(seq)
from board
where bcode= '3' and to_char(logtime, 'yymmdd')= to_char(sysdate, 'yymmdd')

--��ü�۰���
select count(seq)
from board
where bcode= '3'
