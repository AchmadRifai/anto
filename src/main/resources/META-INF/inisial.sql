create table suplier(kode varchar(20)primary key,nama varchar(40)not null,almt text not null,tlp varchar(20)not null,hapus boolean not null);
insert into suplier values('as','Agung Sentosa','Jl Basuki Rahmad','39829',false);
create table sales(dari varchar(20)not null references suplier(kode)on update cascade on delete cascade,nama varchar(40)not null,nik varchar(30)not 
null,tlp varchar(20)not null);
create table barang(kode varchar(30)primary key,nm varchar(30)not null,beli money not null,jual money not null,ket text not null,stok double 
precision not null,sat varchar(5)not null,hapus boolean not null);
create table pasok(kode varchar(40)primary key,tgl date not null,jam time not null,sup varchar(20)not null references suplier(kode)on update cascade 
on delete cascade,tot money not null,ket text not null,hapus boolean not null);
create table item_psk(pas varchar(40)not null references pasok(kode)on update cascade on delete cascade,brg varchar(30)not null references barang(kode)on 
update cascade on delete cascade,jum double precision not null,sat money not null,tot money not null,ubah money not null,hapus boolean not null);
create table pelanggan(kode varchar(30)primary key,nik varchar(30)not null,nama varchar(40)not null,almt text not null,tlp varchar(20)not null,hapus 
boolean not null);insert into pelanggan values('anom','-','anonym','-','-',false);
create table jual(nota varchar(50)primary key,tgl date not null,jam time not null,pel varchar(30)not null references pelanggan(kode)on update cascade 
on delete cascade,tot money not null,byr money not null,kbl money not null,ket text not null,hapus boolean not null);
create table item_ju(nota varchar(50)not null references jual(nota)on update cascade on delete cascade,brg varchar(30)not null references barang(kode)on 
update cascade on delete cascade,jum double precision not null,sat money not null,tot money not null);
