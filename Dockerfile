FROM golang:alpine
RUN mkdir /app
COPY . /app
WORKDIR /app/gin-hello-world
RUN go build -o main .
CMD ["/app/gin-hello-world/main"]