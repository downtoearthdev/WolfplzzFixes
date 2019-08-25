#WolfplzzFixes
**Commands:**
- /deathlocation *wolfplzzfixes.deathlocation*
- /maintenance <on/off\> *wolfplzzfixes.maintenance*
- /rtp *wolfplzzfixes.rtp*
- /discord
- /twitch
- /website
- /youtube
- /teamspeak

**Configuration**

~~~
maintenance-mode:
  motd: "Maintenance Mode" //MOTD on server list while in Maintenance mode
  kick-message: "Server is undergoing maintenance." //The message players that join while in Maintenance are kicked with
phantom-control:
  days-to-appear: 5 //How many days will it take for phantoms to arrive?
  reset-nap-seconds: 3 //How many seconds in a bed will make phantoms leave?
  rested-message: "The creatures no longer find you interesting..." //What message do we send the players when the phantoms leave?
rtp:
  water: false //Do we spawn in water?
  min_spawn_blocks: 1000 //How close to Spawn can we appear?
  min_border_blocks: 100 //How close to the border can we appear?
  cooldown: 10 //How many minutes do we wait to rtp again?
  cooldown-message: "You still have {timeLeft} left!" //What message do we send the players when they try to use the command too soon?
socials: //Links to social media. 
  discord-link: "http://discordapp/something"
  discord-msg: "Check out our discord over at [&5{discord}&r]"
  twitch-link: "http://twitch.tv/somthing"
  twitch-msg: "Check out our twitch over at [&5{twitch}&r]"
  website-link: "http://wolfplzz.ga"
  website-msg: "Check out our website over at [&5{website}&r]"
  youtube-link: "http://youtu.be/wolfplzz"
  youtube-msg: "Check out our youtube over at [&5{youtube}&r]"
  teamspeak-link: "http://teamspeaksomething.com"
  teamspeak-msg: "Check out our teamspeak over at [&5{teamspeak}&r]"
  ~~~
  