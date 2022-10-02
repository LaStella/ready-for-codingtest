// 박성원
// https://www.acmicpc.net/problem/1086

/*
우선 주어진 수들을 가지고 하나의 수를 만든다면 너무 큰 수가 나오므로 나머지 계산하는데 어려움이 있습니다.
어떤 두 수를 a, b라 할 때 두 수를 붙인 x는 다음과 같이 표현할 수 있습니다
x = a * 10^(a_length) + b

나머지연산을 적용하면 다음과 같습니다.
(a mod k)(10^(a_length) mod k)mod k

 */