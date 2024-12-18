FROM bellsoft/liberica-openjdk-debian:21

ENV WORK /backend

COPY entrypoint.sh $WORK/
RUN chmod +x $WORK/entrypoint.sh

WORKDIR $WORK
ENTRYPOINT ["./entrypoint.sh"]
