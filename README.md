
# Knockable

Knockable is a light-weight Fabric Minecraft mod which enables
you to knock on blocks to make your presence known.

Just use the newly added `"Knock"` Keybind on any valid block.

## ✨ Features

- Knock on blocks to play a knocking sound
- Depending on the targeted block different functionality can be used
  - BlockEntity with an Inventory: Pitch of the sound is based on how many Item Slots are not empty
  - Blocks without Inventories: Pitch of the sound is based on distance to the targeted block

## 🔧 Configuration

- Knockable blocks can be specified with the newly added [knockable:knockable](src/main/generated/data/knockable/tags/blocks/knockable.json) block tag, but by default, all blocks in door, trapdoor, chests and barrel block tags are included 
- Using the `"knockingRange"` Gamerule, the max distance of valid knocking can be specified

<div style="text-align: center;">
<br>
<a href="https://fabricmc.net/"><img
    src="https://raw.githubusercontent.com/fabricated-atelier/.github/a021bde84febcb68adc69fc7ae60114e8c0902db/assets/badges/bc25/supported_on_fabric_loader.svg"
    alt="Supported on Fabric"
    width="200"
></a>
</div>
