#!/bin/bash
echo "before 실행" >> before_debug.txt
if [ -d /home/ec2-user/apps/liar-game ]; then
    rm -rf /home/ec2-user/apps/liar-game
fi
mkdir -vp /home/ec2-user/apps/liar-game
