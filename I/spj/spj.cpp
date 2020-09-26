#include <cstdio>
#include <cassert>
#include <vector>
#include <set>
#include <map>
#include <string>
#include <cstdlib>
#include <cstring>
#include <algorithm>

using namespace std;
char buffer[1000000];

string read_string(FILE *fp) {
    assert(fscanf(fp,"%10000s",buffer)==1);
    return string(buffer);
}
int read_int(FILE *fp) {
    int temp;
    assert(fscanf(fp,"%d",&temp)==1);
    return temp;
}
long long read_long(FILE *fp) {
    long long temp;
    assert(fscanf(fp,"%lld",&temp)==1);
    return temp;
}
long long read_double(FILE *fp) {
    double temp;
    assert(fscanf(fp,"%lf",&temp)==1);
    return temp;
}
void assert_eof(FILE *fp) {
    assert(fscanf(fp,"%10000s",buffer)!=1);
}

int main(int agrc, char **agrv){

  FILE *in = fopen(agrv[1],"r");
  FILE *out = fopen(agrv[3],"r");
  int n = read_int(in);
  
  map<string, map<string, int> > male_pref;
  map<string, map<string, int> > female_pref;
  map<string, bool> male_visited;
  map<string, bool> female_visited;
  /* 남자, 여자의 이름은 스킵 */
  for (int i = 0; i < 2 * n; i++)
      read_string(in);

  /* 남자의 선호도를 입력 */
  for (int i = 0; i < n; i++) {
    string male = read_string(in);
    male_visited[male] = false;
    for (int i = 0; i < n; i++) {
      string female = read_string(in);
      male_pref[male][female] = i;
    }
  }
  /* 여자의 선호도를 입력 */
  for (int i = 0; i < n; i++) {
    string female = read_string(in);
    female_visited[female] = false;
    for (int i = 0; i < n; i++) {
      string male = read_string(in);
      female_pref[female][male] = i;
    }
  }
  fclose(in);

  map<string, string> male_partner;
  map<string, string> female_partner;
  char *male_name, *female_name, *token;
  int len = 0;
  while (fgets(buffer, sizeof(buffer), out) != NULL) {
    if (strchr(buffer, '\n'))
      *strchr(buffer, '\n') = '\0';
    male_name = strtok(buffer, " ");
    assert(male_name != NULL);
    female_name = strtok(NULL, " ");
    assert(female_name != NULL);
    token = strtok(NULL, " ");
    assert(token == NULL);
    string male = string(male_name);
    string female = string(female_name);
    male_visited[male] = true;
    female_visited[female] = true;
    assert(male_pref.find(male) != male_pref.end());
    assert(female_pref.find(female) != female_pref.end());
    male_partner[male] = female;
    female_partner[female] = male;
    len++;
  }
  assert(len == n);
  assert_eof(out);
  fclose(out);

  /* 모든 남, 녀의 이름이 나왔는가? */
  for (auto const & male : male_visited)
    assert(male.second == true);
  for (auto const & female : female_visited)
    assert(female.second == true);

  for (auto const & male : male_pref) {
    string female = male_partner[male.first];
    int current_male_pref = male_pref[male.first][male_partner[male.first]];
    for (auto const & other_female : female_pref) {
      /* 지금 파트너보다 더 선호도가 높은 상대가 있을 때 */
      if (male_pref[male.first][other_female.first] < current_male_pref) {
        int current_other_female_pref = female_pref[other_female.first][female_partner[other_female.first]];
        /* 그 파트너도 나를 더 선호할 경우 */
        assert(female_pref[other_female.first][male.first] >= current_other_female_pref);
      }
    }
  }
  return 0;
}
