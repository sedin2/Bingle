import useFetcher from './useFetcher';

const getTeamListURL = 'http://localhost:8080/teams';

type Team = {
  name: string;
  orderPoint: number;
  colorImageUrl: string;
};

export default function useTeams(): {
  teams: Team[];
  error: any;
  isLoading: Boolean;
} {
  const { data, error, isLoading } = useFetcher(getTeamListURL, 'GET');
  const teams = data?.data?.teams as Team[];
  teams?.sort((teamA: any, teamB: any) => {
    if (teamA.orderPoint > teamB.orderPoint) return 1;
    else return 0;
  });
  return { teams, error, isLoading };
}
