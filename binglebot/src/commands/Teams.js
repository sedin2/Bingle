const { SlashCommandBuilder } = require('discord.js');

module.exports = {
  data: new SlashCommandBuilder()
    .setName('팀정보')
    .setDescription('LCK Team Information'),
  async execute(interaction) {
    await interaction.reply('TEAM!');
  },
};
