--alter table replays.player_name drop constraint player_steam_id_pkey;
alter table replays.player_name add column id uuid primary key;