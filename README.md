# Pointless Piglins 🐷🔱

**Stop zombified piglins from spawning with golden spears and ruining your gold farm.**

When Minecraft 26.x added golden spears, zombified piglins started spawning with them. 
In a gold farm, an angry zombified piglin with a spear can chain-kill its buddies, 
destroying both XP and loot drops. This mod fixes that.

## How it works

On natural spawn, if a zombified piglin is holding a golden spear, the mod quietly 
removes it. The piglin keeps its normal weapon (golden sword) and behavior — 
it just won't accidentally massacre its own kind.

## Gamerule

- `/gamerule pointlessPiglins true` (default) — Strip golden spears on natural spawn
- `/gamerule pointlessPiglins false` — Vanilla behavior

## Requirements

- Minecraft 26.1.2
- Fabric Loader 0.15+
- Fabric API

## License

MIT
