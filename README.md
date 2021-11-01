# gilded-tros-java
A solution to the gilded-tros refactoring problem

general notes:

Sell-in -> days of life left - reduces by 1 per day
quality -> quantifier -> changes by some value per day depending on item type and (potentially) the nr of days left in life
					  -> between 0 and 50 (both inclusive)
					  -> legendary set at 80

item types:

Good Wine:
- increases in quality with age
- quality increase doubles in speed when sell by date has passed
- sell-in irrelevant?

B-DAWG Keychain:
- legendary item
- no sel-in
- no reduction in quality (set to 80)

Backstage passes:
- quality increases as sell-in approaches 0
	-> 6 - 10 days left: quality increases by 2
	-> 0 - 5 days left: quality increases by 3
	-> after: quality drops to 0
	
Smelly items ("Duplicate Code", "Long Methods", "Ugly Variable Names")
- degrade twice as fast in quality as normal


systeem voor item type -> value updater generiek te make
-> items en types from config file?
generic rule applicator ?

processor interface
	-> map van item name en item type enum that can return the correct implementation based on the enum
	-> has a get item type method
	-> interface contains default final implementation for max en minimum values
		-> inject those values
something like
