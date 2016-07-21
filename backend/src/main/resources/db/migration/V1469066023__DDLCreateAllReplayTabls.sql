-- some chicken before egg issues hard to work out in one go
--https://www.lucidchart.com/invitations/accept/063572b5-bf52-485f-a771-030b740a41f8
set SEARCH_PATH = 'replays', 'public';

create table map (
  workshop_id bigint primary key,
  map_name varchar(256) not null
);

create table game (
  id uuid primary key,
  time timestamp without time zone not null,
  player_count integer not null,
  map_id bigint references map (workshop_id) on delete cascade,
  host_name varchar(256) not null,
  game_mode varchar(256) not null
);

create table parsed_replays (
  id uuid primary key,
  file_name varchar(512) not null,
  game_id uuid references game (id)
);

create table player (
  steam_id bigint primary key
);

-- join tables
create table player_game (
  id uuid primary key,
  player_steam_id bigint REFERENCES player (steam_id),
  game_id uuid references game (id)
);

create table player_name (
  player_steam_id bigint REFERENCES player (steam_id),
  name varchar(256) not null
);

create table player_game_score (
  game_id uuid references game (id),
  player_steam_id bigint references player (steam_id),
  score int not null,
  team int not null
);

