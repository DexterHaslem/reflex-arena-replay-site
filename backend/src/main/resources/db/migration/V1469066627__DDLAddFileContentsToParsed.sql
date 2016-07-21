-- store whole file in db
drop table replays.parsed_replays;

create table replays.file(
  id uuid primary key,
  contents bytea not null
);

create table replays.file_info(
  id uuid primary key,
  name varchar(256) not null,
  size int not null,
  contents_id uuid references replays.file (id),
  game_id uuid references replays.game (id)
);
