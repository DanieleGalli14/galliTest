Breve introduzione al funzionamento del progetto.

Per farlo partire, basta fa eseguire il main nella classe "galliTestApplication".
Se si usa Eclipse Spring Toolsuite, basterà farlo dal boot dashboard.

Il back-end funziona in localhost:8080.
I metodi accettati dal Back-End sono:

POST /point, che serve per creare un nuovo punto, tramite JSON nel formato { "x" : ..., "y" : ...}

GET /space, che restituisce la lista dei punti presenti nello spazio

GET /lines/{n}, che restituisce un set di segmenti che passano attraverso almeno n punti

DELETE /space, che elimina tutti i punti nello spazio


Per testare il back-end, è stato utilizzato il client REST Postman.

Tramite Postman, si apre una scheda, si seleziona il tipo di chiamata (GET, POST, PUT, DELETE), nella casella di testo centrale si scrive la URL da chiamare, e si invia premendo SEND.
Per inviare degli oggetti di tipo JSON, si seleziona la scheda "Body", poi "raw", e poi dalla scrollbar che apparirà "JSON". A questo punto sarà possibile scrivere i JSON.


