const { SlashCommandBuilder } = require('discord.js');

module.exports = {
  data: new SlashCommandBuilder()
    .setName('매치정보')
    .setDescription('LCK Match Information'),
  async execute(interaction) {
    await interaction.reply('MATCH!');
  },
};
