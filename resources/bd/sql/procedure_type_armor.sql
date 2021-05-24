use sgda1;

delimiter $$
drop procedure if exists type_armor $$
create procedure type_armor(IN type_armor VARCHAR(25))
begin
	select a.name, a2.mobility, a2.resilience, a2.recovery, a2.dicipline, a2.intellect, a2.strenght
    from table_armor a , table_armor_attributes a1, table_attributes a2
    where a.cod_armor=a1.cod_armor and a1.cod_attributes=a2.cod_attributes
    and a.type=type_armor;
end $$
delimiter ;

select * from table_armor;
#select * from table_attributes;
#insert into table_armor_attributes(cod_armor, cod_attributes) values(2, 1);
#select * from table_armor_attributes;

call type_armor("b");