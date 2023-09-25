const { SlashCommandBuilder, EmbedBuilder } = require('discord.js');

const fetcher = require('../utils/fetcher');
const URI_OF_TEAM_API = 'http://localhost:8080/teams';

const makeEmbedMessageForTeams = (teamsArray) => {
  let embedMessageForTeams = new EmbedBuilder()
    .setColor(0x0099ff)
    .setTitle('LCK 팀 정보')
    .setURL('https://localhost:3000')
    .setAuthor({
      name: 'Bingle Bot',
      url: 'https://localhost:3000',
    })
    .setDescription('LCK 팀 정보 설명')
    .setFooter({ text: 'LCK 팀 정보 footer' });

  teamsArray.forEach((team) => {
    embedMessageForTeams
      .addFields({
        name: '팀명',
        value: team.name,
      })
      .addFields({
        name: '순위점수',
        value: team.orderPoint.toString(),
        inline: true,
      });
  });
  return embedMessageForTeams;
};

module.exports = {
  data: new SlashCommandBuilder()
    .setName('팀정보')
    .setDescription('LCK Team Information'),
  async execute(interaction) {
    const { data } = await fetcher(URI_OF_TEAM_API);
    if (data.code != 'OK') {
      throw new Error(datamessage);
    }
    const embedMessage = makeEmbedMessageForTeams(data.data.teams);
    const testembedMessage = {
      title: 'GEN G',
      image: {
        url: data.data.teams[0].imageUrl,
      },
      fields: [
        {
          name: '순위점수',
          value: data.data.teams[0].orderPoint.toString(),
        },
      ],
    };
    await interaction.reply({ embeds: [embedMessage, testembedMessage] });
  },
};
