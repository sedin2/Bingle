const { SlashCommandBuilder } = require('discord.js');

const fetcher = require('../utils/fetcher');

const URI_OF_MATCH_API = 'http://localhost:8080/matches';

const makeEmbedMessageForTeams = (teamsArray) => {};

module.exports = {
  data: new SlashCommandBuilder()
    .setName('매치정보')
    .setDescription('LCK Match Information'),
  async execute(interaction) {
    const response = await fetcher(URI_OF_MATCH_API);
    if (response.code != 'OK') {
      throw new Error(response.message);
    }
    await interaction.reply({ embeds: exampleEmbed });
  },
};
