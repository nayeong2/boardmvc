-- 게시판 메뉴
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