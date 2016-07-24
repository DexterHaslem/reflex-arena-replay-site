alter table replays.game drop constraint game_map_id_fkey;
alter table replays.map drop constraint map_pkey;

-- NOTE: map needs to be empty for this to work
alter table replays.map add column id uuid primary key default null;

--alter table replays.game alter column map_id type uuid;
alter table replays.game drop column map_id;
alter table replays.game add column map_id uuid references replays.map(id);
--alter table replays.game
--  add CONSTRAINT game_map_id_fkey foreign key (map_id)
--  references replays.map(id) on delete cascade;