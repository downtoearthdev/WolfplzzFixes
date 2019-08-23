# WolfplzzFixes
WolfplzzFixes plugin for Wolfplzz Community

maintenance-mode:
  motd: "Maintenance Mode"
  kick-message: "Server is undergoing maintenance."
phantom-control:
  days-to-appear: 5
  reset-nap-seconds: 3
  rested-message: "The creatures no longer find you interesting..."
rtp:
  water: false
  min_spawn_blocks: 1000
  min_border_blocks: 100
  cooldown: 10
  cooldown-message: "You still have {timeLeft} left!"
socials:
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
  
  commands:
  deathlocation:
    usage: "/deathlocation"
    description: "Shows coords of last location you died"
    permission: wolfplzzfixes.deathlocation
  maintenance:
    usage: "/maintenance <on|off>"
    description: "Turns server maintenance mode on or off"
    permission: wolfplzzfixes.maintenance
  rtp:
    usage: "/rtp"
    description: "Teleports to a random location within the World Border"
    permission: wolfplzzfixes.rtp
  discord:
    usage: "/discord"
    description: "Displays Discord link"
  twitch:
    usage: "/twitch"
    description: "Displays Twitch link"
  website:
    usage: "/website"
    description: "Displays Website link"
  youtube:
    usage: "/youtube"
    description: "Displays Youtube link"
  teamspeak:
    usage: "/teamspeak"
    description: "Displays Teamspeak link"
permissions:
  wolfplzzfixes.maintenance:
    description: Grants ability to use maintenance mode
    default: op
  wolfplzzfixes.deathlocation:
    description: Grants ability to use /deathlocation
    default: op
  wolfplzzfixes.rtp:
    description: Grants ability to use /rtp
    default: op
