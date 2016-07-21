drop table replays.player_game_score;


alter table replays.player_game add column score int;
alter table replays.player_game add column team int;