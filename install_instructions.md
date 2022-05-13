# Script to be execute before starting the broanciaq java app:
	##	create database broanciaq;
# Script to be execute after starting the broanciaq java app:
	## insert into role values(1,CURRENT_USER,now(),'ADMIN',CURRENT_USER,now());
	## insert into role values(2,CURRENT_USER,now(),'USER',CURRENT_USER,now());
	## insert into role values(3,CURRENT_USER,now(),'GUEST',CURRENT_USER,now());
	## insert into role values(4,CURRENT_USER,now(),'PORTALUSER',CURRENT_USER,now());
	
### Important commands:
	#### To use the created database : \c broanciaq
	#### To see the list tables : \dt
	
	
	user name - postgres
	password  - Broan@TWX01
	
	psql -d broanciaq -U  postgres -W
	
	