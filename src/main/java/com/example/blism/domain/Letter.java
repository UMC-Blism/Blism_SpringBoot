package com.example.blism.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "letters")
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mailbox와 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mailbox_id", nullable = false)
    private Mailbox mailbox;

    // 발신자, 수신자 Member 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;

    @Lob
    private String content;

    @Column(nullable = false)
    private Boolean visibility;

    @Column(length = 255)
    private String photoUrl;

    @Column(nullable = false)
    private Integer doorNum;        // 0 ~ 4

    @Column(nullable = false)
    private Integer colorNum;       // 0 ~ 4

    @Column(nullable = false)
    private Integer decorationNum;  // 0 ~ 4

    // Letter에 달린 replies
    @OneToMany(mappedBy = "letter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

}
