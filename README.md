# Hardcore Minecraft Mod
Simple mod that translates the old minecraft behaviour to the current version.

### Features:
- Disable Food
	- Removes current hunger bar.
	- Food only restores health points
- Low Brightness
	- Brightness is always set to moody
- Disable Sprint
	- Sprinting is removed
- Unstackable Food
	- Food cannot be stacked
- Disable Bed
	- Beds are only used to set respawn points
- Weaker Shield
	- Shields breaks after one use
- Minecraft Version Overlay Text
	- Remember "Minecraft Alpha v1.2.5" text on the top left screen?
	- It's back
- Disable Cooldown
	- 1.9 combat system is removed
	- Axes always deal 1HP damage
- Disable Debug
	- Disables the F3 debug screen

### Configuration
Find hardcore-mc-features.json file inside the Minecraft installation folder and open it.
The JSON files has the following structure:
`"feature-name": true|false`
Change true or false for each feature you want to enable or disable.

###### Why JSON file?
I plan the configuration to be relative to each world. That's why I choose json file configuration instead of the classic forge configuration.

### Multiplayer
Should work, but I have yet to test it.

### License
Feel free to use, edit and reditribute it, just at least mention my name :)