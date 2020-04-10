public class Spreadsheet {
    Object[][] sheet = new Object[10][10];  // 시트 역할을 할 2차원 Object 배열

    void set_value(String idx, Object value){
        int column = getColumn(idx); // 맨 밑에 주석
        int row = getRow(idx);  // 맨 밑에 주석

        sheet[column][row] = value; // 사용자가 입력한 인덱스 위치에, 입력한 값을 저장
    }

    Object get_value(String idx){
        int column = getColumn(idx);
        int row = getRow(idx);

        Object result = sheet[column][row];

        if( result == null ){ return "none";}
        return result;
    }

    @Override
    public String toString() {   // 객체 출력 시 보여줄 문자열 값을 리턴하는 함수
        String result = "";     // 이따가 리턴할 문자열 담을 String 변수

        for( int i = 0 ; i < 10 ; i++ ){        // row 순회
            for( int j = 0 ; j < 10 ; j++ ){    // column 순회
                result += String.format("%-10s",sheet[j][i] == null ? "" : sheet[j][i]);
                // 문제 출력 예시처럼 줄을 좀 맞추려고 포맷을 사용함 ( String.format )
                // %-10s 는 10자리를 확보하고 왼쪽부터 문자열을 채우는 포맷
                // 포맷안에 문자열을 넣을건데, java의 경우엔 Object형이 초기화가 되지않으면 null 값을 토해냄.
                // null 값일 경우 빈 문자열( "" ) 을 포맷에 채우고, 아닐경우 사용자가 입력한 값( sheet[j][i] )을 채움

                if( j == 9 ) {         // column 순회중에 마지막 column 인 경우
                    result += "\n";    // 줄바꿈 문자를 추가
                    break;             // for문(column 순회하는) 종료
                }
                result += ",";         // 여기 코드가 실행 됐다는건 마지막 column 이 아니라는 거니까 콤마를 추가
            }
        }
        return result;
    }

    int getColumn(String idx){
        return Character.toUpperCase( idx.charAt(0) ) - 65;
    }
    // 사용자가 A5 , a9 , C10 요딴식으로 입력하기 때문에
    // column으로 사용할 A , a , C 얘네를 떼옴( charAt(0) )
    // A, a 처럼 사용자는 대소문자 막 입력하니까, 내부적으로 무조건 대문자로 처리( toUpperCase )
    // 최종적으로 가져온 A,B,C, ... 이런 애들을 배열의 인덱스로 사용해야 하기 때문에 0, 1, 2, ... 로 바꿔야함
    // A의 아스키코드값이 65니까 65를 빼주면 A = 0 , B = 1 , C = 2 , ... 가 됨

    int getRow(String idx){
        return Integer.parseInt( idx.substring(1) ) - 1;
    }
    // A5, a9, C10 여기서 A, a, C를 제외한 부분을 가져와서
    // 배열의 인덱스로 사용하기 위해 1을 빼줌
}
