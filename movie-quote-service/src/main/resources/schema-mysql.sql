create table if not exists movie (
  id int(11) not null auto_increment,
  name varchar(100) not null,
  primary key (id)
) ENGINE=InnoDB;

create table if not exists movie_quote (
  id int(11) not null auto_increment,
  movie_id int(11) not null,
  quote varchar(200) not null,
  primary key (id),
  foreign key (movie_id) references movie(id)
) ENGINE=InnoDB;
