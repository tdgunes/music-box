grammar Musicbox;


start  : '(' STRING (OPERATOR start)* ')' | STRING |  '(' (start OPERATOR)* STRING  ')' ;

 //"(playedBy:Metallica & (playedBy:Metallica | playedBy:ACDC))";

OPERATOR: '|' | '&';
STRING :  ('a'..'z' | 'A'..'Z' | ':' | '/' | '-')+;
WS  :   [ \t\n\r]+ -> skip ;




/*
start :a
    fieldlist;

fieldlist :
    field (COMMA field)* ;

field :
    STRING L fieldlist R
    | STRING ;

STRING : ('a'..'z' | 'A'..'Z')+;
COMMA : ',' ;
L   :   '(';
R   :   ')';*/